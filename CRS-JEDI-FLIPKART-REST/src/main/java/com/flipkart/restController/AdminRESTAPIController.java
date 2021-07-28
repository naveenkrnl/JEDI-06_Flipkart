package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.constant.NotificationType;
import com.flipkart.exception.*;
import com.flipkart.utils.StringUtils;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/admin")
public class AdminRESTAPIController {


    AdminInterface adminOperation = AdminOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, I am admin!";
    }

    @Path("/login")
    @POST
    @Produces("text/plain")
    public javax.ws.rs.core.Response loginUser(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        return javax.ws.rs.core.Response.status(200).entity("Login successful").build();
    }

    /**
     * Method to add Professor to DB
     */
    @POST
    @Path("/addProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) {

        try {
            adminOperation.addProfessor(professor);

        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            StringUtils.printErrorMessage(e.getMessage());
            Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
        return Response.status(200).entity("Operation Successful").build();

    }

    /**
     * Method to delete Course from catalogue
     * @param courseCode Course Code
     */
    private Response deleteCourse(String courseCode) {

        List<Course> courseList = viewCoursesInCatalogue();

        try {
            adminOperation.deleteCourse(courseCode, courseList);
        } catch (CourseNotFoundException | CourseNotDeletedException e) {
            return Response.status(500).entity("Operation Failed. Error: "+ e.getMessage()).build();
        }

        return Response.status(200).entity("Operation Successful. Course is Deleted.").build();
    }

    /**
     * Method to approve a Student using Student's ID
     */
    @PUT
    @Path("/approveStudent")
    public Response approveStudent(int studentUserIdApproval) {


        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            return Response.status(500).entity("No Student Left to Approve").build();
        }
        StringUtils.printHeading("Approve Student Portal");

        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
            //send notification from system
            notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null, 0, null, null);

        } catch (StudentNotFoundForApprovalException e) {
            return Response.status(500).entity("Operation Failed. Error: "+e.getMessage()).build();
        }
        catch (Exception e) {
            return Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }

        return Response.status(200).entity("Operation Successful. Student Approved").build();
    }

    /**
     * Method to display courses in catalogue
     *
     */
    @GET
    @Path("/viewCoursesInCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses(1);
        if (courseList.size() == 0) {
            return courseList;
        }

        courseList.forEach((course)->{
            if (course.getInstructorId() == null || course.getInstructorId().isEmpty())
                course.setInstructorId("No Professor");
        });
        return  courseList;
    }


}
