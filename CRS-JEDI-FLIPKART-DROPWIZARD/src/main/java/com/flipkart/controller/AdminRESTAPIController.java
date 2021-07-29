package com.flipkart.controller;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.exception.*;
import com.flipkart.utils.StringUtils;

import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.constant.NotificationType;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/admin")
public class AdminRESTAPIController {

    AdminInterface adminOperation = AdminOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    /**
     * Method to add Professor to DB
     * @param professor: Professor object containing details passed from the client
     * @return Success/Failure of addition of professor
     */
    @POST
    @Path("/addProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(Professor professor) {
        try {
            adminOperation.addProfessor(professor);

        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            StringUtils.printErrorMessage(e.getMessage());
            return Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
        return Response.status(200).entity("Operation Successful").build();
    }

    /**
     * Method to assign Course to a Professor
     * @param course: Course object containing details passed from the client
     * @return Success/failure of professor assignment
     */
    @PUT
    @Path("/assignCourseToProfessor")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response assignCourseToProfessor(Course course) {
        try {
            String courseCode = course.getCourseCode();
            String userId = course.getInstructorId();
            System.out.println(courseCode + " " + userId);
            adminOperation.assignCourse(courseCode, userId);

        } catch (CourseNotFoundException | UserNotFoundException e) {

            Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
        return Response.status(200).entity("Operation Successful").build();
    }

    /**
     * Method to delete Course from catalogue
     *  @param course: Course object containing details passed from the client
     *  @return Success/failure of course deletion
     */
    @DELETE
    @Path("/deleteCourse")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCourse(Course course) {

        List<Course> courseList = viewCoursesInCatalogue();

        try {
            adminOperation.deleteCourse(course.getCourseCode(), courseList);
        } catch (CourseNotFoundException | CourseNotDeletedException e) {
            return Response.status(500).entity("Operation Failed. Error: "+ e.getMessage()).build();
        }

        return Response.status(200).entity("Operation Successful. Course is Deleted.").build();
    }

    /**
     * Method to approve a Student using Student's ID
     * @param studentUserIdApproval: Student Id of student to approve
     * @return Success/failure of student approval
     */
    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response approveStudent(int studentUserIdApproval) {


        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            return Response.status(500).entity("No Student Left to Approve").build();
        }

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
     * @return List of courses in the catalogue
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

    /**
     * Method to add Course to catalogue
     * @param course: Course object containing details passed from the client
     * @return Success/failure of addition of course to the catalogue
     */
    @PUT
    @Path("/addCourseToCatalogue")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCourseToCatalogue(Course course) {

        try {
            adminOperation.addCourse(course, viewCoursesInCatalogue());
        } catch (CourseFoundException e) {
            return Response.status(500).entity("Operation Failed. "+e.getMessage()).build();
        }
       return Response.status(200).entity("Operation Successful").build();
    }

    /**
     * Method to view student who are pending admin approval
     * @return List of students who are pending approval
     */
    @GET
    @Path("/viewPendingAdmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingAdmissions() {
        List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();
        return pendingStudentsList;
    }
}


