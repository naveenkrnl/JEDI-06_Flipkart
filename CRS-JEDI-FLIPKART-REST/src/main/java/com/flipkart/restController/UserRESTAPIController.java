package com.flipkart.restController;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


@Path("/user")
public class UserRESTAPIController {


    @Path("/login")
    @POST
    @Produces("text/plain")
    public Response loginUser(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        return Response.status(200).entity("Login successful").build();
//        String userId, password;
//        try {
//            StringUtils.printHeading("Login Portal", 100);
//            System.out.println("Email:");
//            userId = sc.next();
//            System.out.println("Password:");
//            password = sc.next();
//            loggedin = userInterface.verifyCredentials(userId, password);
//            //2 cases
//            //true->role->student->approved
//            if (loggedin) {
//                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//                LocalDateTime myDateObj = LocalDateTime.now();
//
//                String formattedDate = myDateObj.format(myFormatObj);
//
//
//                //System.out.println("Welcome "+userId);
//                String role = userInterface.getRole(userId);
//                Role userRole = Role.stringToName(role);
//                switch (userRole) {
//                    case ADMIN:
//                        StringUtils.printSuccessMessage(formattedDate + " Login Successful as Admin", 100);
//                        AdminCRSMenu adminMenu = new AdminCRSMenu();
//                        adminMenu.createMenu();
//                        break;
//                    case PROFESSOR:
//                        StringUtils.printSuccessMessage(formattedDate + " Login Successful for Professor");
//                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
//                        professorMenu.createMenu(userId);
//
//                        break;
//                    case STUDENT:
//
//                        int studentId = studentInterface.getStudentId(userId);
//                        boolean isApproved = studentInterface.isApproved(studentId);
//                        if (isApproved) {
//                            StringUtils.printSuccessMessage(formattedDate + " Login Successful for Student");
//                            StudentCRSMenu studentMenu = new StudentCRSMenu();
//                            studentMenu.create_menu(studentId);
//
//                        } else {
//                            StringUtils.printErrorMessage("Failed to login, you have not been approved by the administration!");
//                            loggedin = false;
//                        }
//                        break;
//                }
//
//
//            } else {
//                StringUtils.printErrorMessage("Invalid Credentials!");
//            }
//
//        } catch (UserNotFoundException ex) {
//            StringUtils.printErrorMessage(ex.getMessage());
//        }
    }



}
