package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.bean.*;
import com.flipkart.business.*;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Path("/student")
public class StudentRESTAPIController {

    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface=NotificationOperation.getInstance();
    private boolean is_registered;



    @Path("/login")
    @POST
    @Produces("text/plain")
    public Response loginUser(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        return Response.status(200).entity("Login successful").build();
    }

    @Path("/viewCatalogue")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(@QueryParam("studentId") int studentId) throws SQLException {
        return registrationInterface.viewCourses(studentId);
    }
}