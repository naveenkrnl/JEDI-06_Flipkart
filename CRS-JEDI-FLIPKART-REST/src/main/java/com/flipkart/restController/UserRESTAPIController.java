package com.flipkart.restController;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserRESTAPIController {

    final static UserInterface userInterface = UserOperation.getInstance();
    final static StudentInterface studentInterface = StudentOperation.getInstance();
    final static NotificationInterface notificationInterface = NotificationOperation.getInstance();
    final static AdminInterface adminInterface = AdminOperation.getInstance();

    /**
     * Method for user login
     * 
     * @param user: User object containing user details from client
     * @return Success/failure of user login
     */
    @Path("/login")
    @POST
    @Produces("text/plain")
    public Response loginUser(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        // Input validation req?
        if (userId == null || userId.trim().length() == 0) {
            return Response.status(400).entity("UserId can not be empty").build();
        } else if (password == null || password.trim().length() == 0) {
            return Response.status(400).entity("Password can not be empty").build();
        }

        userId = userId.trim();
        password = password.trim();

        try {
            boolean loggedin = userInterface.verifyCredentials(userId, password);

            if (!loggedin)
                return Response.status(401).entity("Login failed.Invalid Credentials").build();

            String role = userInterface.getRole(userId);
            Role userRole = Role.stringToName(role);
            switch (userRole) {
                case ADMIN:
                    return Response.status(200).entity("Login successful as Admin").build();
                case PROFESSOR:
                    return Response.status(200).entity("Login successful as Professor").build();
                case STUDENT:
                    int studentId = studentInterface.getStudentId(userId);
                    return (studentInterface.isApproved(studentId)
                            ? Response.status(200).entity("Login successful as Student")
                            : Response.status(401).entity("Login failed.You have not been approved by admin yet"))
                                    .build();
                default:
                    return Response.status(401).entity("User Role Invalid.Please contact admin").build();
            }
        } catch (UserNotFoundException ex) {
            return Response.status(401).entity("Login Failed." + ex.getMessage()).build();
        }
    }

    /**
     * Method for student registration
     * 
     * @param student: Student object containing student details passed from the
     *                 admin
     * @return Success/failure of student registration
     */
    @Path("/studentRegistration")
    @POST
    @Produces("text/plain")
    public Response registerNewStudent(Student student) {
        student.setApproved(false);
        student.setRole(Role.STUDENT);
        student.setStudentId(0);

        String userId = student.getUserId();
        String name = student.getName();
        String password = student.getPassword();
        String address = student.getAddress();
        String country = student.getCountry();
        String branchName = student.getBranchName();
        Gender gender = student.getGender();
        int batch = student.getBatch();
        String role = userInterface.getRole(userId);

        // Input validation req?
        if (userId == null || userId.trim().length() == 0) {
            return Response.status(400).entity("UserId can not be empty").build();
        } else if (name == null || name.trim().length() == 0) {
            return Response.status(400).entity("Name can not be empty").build();
        } else if (password == null || password.trim().length() == 0) {
            return Response.status(400).entity("Password can not be empty").build();
        } else if (address == null || address.trim().length() == 0) {
            return Response.status(400).entity("Address can not be empty").build();
        } else if (country == null || country.trim().length() == 0) {
            return Response.status(400).entity("Country can not be empty").build();
        } else if (branchName == null || branchName.trim().length() == 0) {
            return Response.status(400).entity("Branch Name can not be empty").build();
        } else if (batch == 0) {
            return Response.status(400).entity("Batch can not be empty or 0").build();
        } else if (gender == null) {
            return Response.status(400).entity("Gender entered is not valid").build();
        } else if (role != null) {
            return Response.status(400).entity("User Id " + student.getUserId() + " already in Use").build();
        }

        userId = userId.trim();
        name = name.trim();
        password = password.trim();
        address = address.trim();
        country = country.trim();
        branchName = branchName.trim();

        try {
            int newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address,
                    country);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0, null, null);
            return Response.status(201).entity("Student Successfully Registered!").build();
        } catch (Exception ex) {
            return Response.status(400)
                    .entity("Something went wrong! not registered. Please try again" + ex.getMessage()).build();
        }
    }

    /**
     * Method to update password
     * 
     * @param userId:      User Id
     * @param newPassword: new password
     * @param oldPassword: old password
     * @return Success/failure of password change
     */
    @Path("/updatePassword")
    @PUT
    @Produces("text/plain")
    public Response registerNewStudent(
            @NotNull @Email(message = "Email not in right") @QueryParam("userId") String userId,
            @NotNull @QueryParam("newPassword") String newPassword,
            @NotNull @QueryParam("oldPassword") String oldPassword) {

        // Input validation req?
        if (userId == null || userId.trim().length() == 0) {
            return Response.status(400).entity("UserId can not be empty").build();
        } else if (oldPassword == null || oldPassword.trim().length() == 0) {
            return Response.status(400).entity("oldPassword can not be empty").build();
        } else if (newPassword == null || newPassword.trim().length() == 0) {
            return Response.status(400).entity("newPassword can not be empty").build();
        }

        userId = userId.trim();
        newPassword = newPassword.trim();
        oldPassword = oldPassword.trim();

        try {
            boolean loggedin = userInterface.verifyCredentials(userId, oldPassword);
            if (!loggedin)
                return Response.status(401).entity("Incorrect Old Password").build();
            boolean isUpdated = userInterface.updatePassword(userId, newPassword);
            if (isUpdated)
                return Response.status(202).entity("Password updated successfully!").build();
            else
                return Response.status(500).entity("Something went wrong, please try again!").build();
        } catch (Exception ex) {
            return Response.status(500).entity("Error Occurred " + ex.getMessage()).build();
        }
    }

    /**
     * Method for admin registration
     * 
     * @param admin: Admin object passed from the client
     * @return Success/failure of admin registration
     */
    @Path("/adminRegistration")
    @POST
    @Produces("text/plain")
    public Response registerNewStudent(Admin admin) {
        admin.setRole(Role.ADMIN);

        String userId = admin.getUserId();
        String name = admin.getName();
        String password = admin.getPassword();
        String address = admin.getAddress();
        String country = admin.getCountry();
        Gender gender = admin.getGender();
        String role = userInterface.getRole(userId);

        // Input validation req?
        if (userId == null || userId.trim().length() == 0) {
            return Response.status(400).entity("UserId can not be empty").build();
        } else if (name == null || name.trim().length() == 0) {
            return Response.status(400).entity("Name can not be empty").build();
        } else if (password == null || password.trim().length() == 0) {
            return Response.status(400).entity("Password can not be empty").build();
        } else if (address == null || address.trim().length() == 0) {
            return Response.status(400).entity("Address can not be empty").build();
        } else if (country == null || country.trim().length() == 0) {
            return Response.status(400).entity("Country can not be empty").build();
        } else if (gender == null) {
            return Response.status(400).entity("Gender entered is not valid").build();
        } else if (role != null) {
            return Response.status(400).entity("User Id " + admin.getUserId() + " already in Use").build();
        }

        userId = userId.trim();
        name = name.trim();
        password = password.trim();
        address = address.trim();
        country = country.trim();

        try {
            // String name, String userID, String password, Gender gender, String
            // address,String country
            int newStudentId = adminInterface.register(name, userId, password, gender, address, country);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0, null, null);
            return Response.status(201).entity("Admin Successfully Registered!").build();
        } catch (Exception ex) {
            return Response.status(400)
                    .entity("Something went wrong! not registered. Please try again" + ex.getMessage()).build();
        }
    }
}
