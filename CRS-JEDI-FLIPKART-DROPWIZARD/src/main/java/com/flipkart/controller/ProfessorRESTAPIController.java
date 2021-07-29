package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constant.Grade;
import com.flipkart.constant.Role;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.validator.ProfessorValidator;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("professor")
public class ProfessorRESTAPIController {
    final static ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    final static UserInterface userInterface = UserOperation.getInstance();

    /**
     * Method to fetch courses taught by the professor
     * @param profId: User Id of the professor
     * @return List of courses taught by the professor
     */
    @Path("/getCourses")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCourses(
            @NotNull
            @QueryParam("profId") String profId) {
        String roleStr = userInterface.getRole(profId);
        if (roleStr == null) {
            return Response.status(401).entity(profId + " is not a Registered").build();
        }
        Role role = Role.stringToName(roleStr);
        if (role != Role.PROFESSOR) {
            return Response.status(401).entity(profId + " is not a professor").build();
        }
        try {
            return Response.ok(professorInterface.getCourses(profId)).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    /**
     * Method to fetch list of students enrolled in professor's courses
     * @param profId: User Id of the professor
     * @return List of enrolled students
     */
    @Path("/getEnrolledStudents")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getEnrolledStudents(
            @NotNull
            @QueryParam("profId") String profId) {
        String roleStr = userInterface.getRole(profId);
        if (roleStr == null) {
            return Response.status(401).entity(profId + " is not a Registered").build();
        }
        Role role = Role.stringToName(roleStr);
        if (role != Role.PROFESSOR) {
            return Response.status(401).entity(profId + " is not a professor").build();
        }

        try {
            return Response.ok(professorInterface.viewEnrolledStudents(profId)).build();
        } catch (Exception ex) {
            return Response.status(500).entity(ex.getMessage()).build();
        }
    }

    /**
     * Method to add student's grade
     * @param profId: User Id of the professor
     * @param password: Password
     * @param studentId: Student Id
     * @param courseCode: Course Code
     * @param grade: Grade
     * @return Success/failure of grade addition
     */
    @Path("/addGrade")
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    public Response addGrade(
            @NotNull
            @QueryParam("profId") String profId,
            @NotNull
            @QueryParam("password") String password,
            @NotNull
            @QueryParam("studentId") int studentId,
            @NotNull
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("grade") Grade grade) {

        try {
            boolean loggedin = userInterface.verifyCredentials(profId, password);
            if (!loggedin)
                return Response.status(401).entity("Can't Add grades.Invalid Credentials").build();
        } catch (UserNotFoundException e) {
            return Response.status(401).entity(profId + " is not a Registered").build();
        }

        String roleStr = userInterface.getRole(profId);
        Role role = Role.stringToName(roleStr);
        if (role != Role.PROFESSOR) {
            return Response.status(401).entity(profId + " is not a professor").build();
        }

        try {
            List<EnrolledStudent> enrolledStudents = professorInterface.viewEnrolledStudents(profId);
            List<Course> coursesEnrolled = professorInterface.getCourses(profId);
            if (ProfessorValidator.isValidStudent(enrolledStudents, studentId)
                    && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode)) {
                professorInterface.addGrade(studentId, courseCode, grade.toString());
                return Response.status(201).entity("Grade added successfully for " + studentId).build();
            } else {
                return Response.status(400).entity("Invalid data entered, try again!").build();
            }
        } catch (GradeNotAddedException ex) {
            return Response.status(500).entity("Grade cannot be added for" + ex.getStudentId()).build();

        } catch (SQLException ex) {
            return Response.status(500).entity("Grade not added, SQL exception occurred " + ex.getMessage()).build();
        }
    }
}