package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.bean.*;
import com.flipkart.business.*;
import com.flipkart.constant.Color;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.StringUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Path("/student")
public class StudentRESTAPIController {

    // Global Declarations Required by Methods
    private static Map<String, Integer> gradeStrToScore;

    static {
        gradeStrToScore = new HashMap<>();
        gradeStrToScore.put("A", 10);
        gradeStrToScore.put("B", 9);
        gradeStrToScore.put("C", 8);
        gradeStrToScore.put("D", 7);
        gradeStrToScore.put("E", 6);
        gradeStrToScore.put("F", 5);
        gradeStrToScore.put("NA", 0);
        gradeStrToScore.put("EX", 0);
    }

    public int getScore(String grade) {
        if (gradeStrToScore.containsKey(grade))
            return gradeStrToScore.get(grade);
        return 0;
    }

    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    private static Logger logger = Logger.getLogger(NotificationDaoOperation.class);

    /**
     * Handle API request for Registering for Courses
     * 
     * @param courseIds: Course Ids List
     * @param studentId: ID of Student
     * @return Success/Failure of Registration
     */
    @Path("/registerCourses")
    @POST
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourses(List<String> courseIds, @QueryParam("studentId") int studentId) {

        // If courses selected are not 6, during start of semester
        if (courseIds.size() != 6) {
            return Response.status(406).entity("Select 6 Courses from Course Catalogue").build();
        }

        // If user is always registered
        boolean is_registered = false;
        try {
            is_registered = registrationInterface.getRegistrationStatus(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if (is_registered) {
            return Response.status(403).entity("Student is Already Registered").build();
        }
        List<Course> courseList = null;

        try {
            courseList = registrationInterface.viewCourses(studentId);
        } catch (SQLException e) {
            return Response.status(404).entity("No course Available for Registration").build();
        }

        Set<String> courseSet = new HashSet<String>();
        for (String courseId : courseIds) {
            if (courseSet.contains(courseId)) {
                return Response.status(403).entity("A course has been entered multiple times").build();
            }

            try {
                registrationInterface.addCourse(courseId, studentId, courseList);
            } catch (CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException
                    | SQLException e) {
                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred. Please try again after some time.").build();
            }
            courseSet.add(courseId);
        }

        try {
            // Mark the Student Registered for semester
            registrationInterface.setRegistrationStatus(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Response.status(500).entity("Some Error Occurred. Please try again after some time.").build();
        }
        return Response.status(200).entity("Courses Registration for Student Successful").build();
    }

    /**
     * Handle API request for Adding a New Course
     * 
     * @param courseId:  Course Id of Course to be added
     * @param studentId: ID of Student
     * @return Success/Failure of Adding the Course
     */
    @Path("/addCourse")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@QueryParam("studentId") int studentId, @QueryParam("courseId") String courseId) {
        boolean is_registered = false;
        try {
            is_registered = registrationInterface.getRegistrationStatus(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        if (is_registered) {
            List<Course> availableCourseList = null;
            try {
                availableCourseList = registrationInterface.viewCourses(studentId);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred").build();
            }

            if (availableCourseList == null)
                return Response.status(404).entity("No Courses Available").build();

            try {
                if (registrationInterface.addCourse(courseId, studentId, availableCourseList)) {
                    return Response.status(200).entity("Course added").build();
                } else {
                    return Response.status(403).entity("You have already registered for this course").build();
                }
            } catch (CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException
                    | SQLException e) {
                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred").build();
            }
        } else {
            return Response.status(403).entity("Please complete semester registration first").build();
        }
    }

    /**
     * Handle API request for Adding a New Course
     * 
     * @param courseId:  Course Id of Course to be added
     * @param studentId: ID of Student
     * @return Success/Failure of Adding the Course
     */
    @Path("/dropCourse")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(@QueryParam("studentId") int studentId, @QueryParam("courseId") String courseId) {
        boolean is_registered = false;
        try {
            is_registered = registrationInterface.getRegistrationStatus(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        if (is_registered) {

            List<Course> availableCourseList = null;
            try {
                availableCourseList = registrationInterface.viewRegisteredCourses(studentId);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred").build();
            }

            if (availableCourseList == null)
                return Response.status(404).entity("No Courses Available").build();

            try {
                if (registrationInterface.dropCourse(courseId, studentId, availableCourseList)) {
                    return Response.status(200).entity("Course Droped Succcessfully").build();
                } else {
                    return Response.status(403).entity("You are not registered for this course").build();
                }
            } catch (CourseNotFoundException | SQLException e) {
                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred").build();
            }
        } else {
            return Response.status(403).entity("Please complete semester registration first").build();
        }
    }

    /**
     * Handle API request for Getting List of Registered Courses for a Student
     * 
     * @param studentId: ID of Student
     * @return List of Registered Courses
     */
    @Path("/viewRegisteredCourses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourses(@QueryParam("studentId") int studentId) {

        List<Course> coursesRegistered = null;
        try {
            coursesRegistered = registrationInterface.viewRegisteredCourses(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return null;
        }

        if (coursesRegistered.isEmpty()) {
            StringUtils.printErrorMessage("You haven't registered for any course");
            return null;
        }

        return coursesRegistered;
    }

    /**
     * Handle API request for Getting Available Courses in the Catalogue
     * 
     * @param studentId: ID of Student
     * @return List of Available Courses
     */
    @Path("/viewAvailableCourses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(@QueryParam("studentId") int studentId) throws SQLException {
        return registrationInterface.viewCourses(studentId);
    }

    /**
     * Handle API request for Getting Grade Card
     * 
     * @param studentId: ID of Student
     * @return Grade Card of Student
     */
    @Path("/viewGradeCard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGrade> viewGradeCard(@QueryParam("studentId") int studentId) {
        List<StudentGrade> gradeCard = null;
        try {
            gradeCard = registrationInterface.viewGradeCard(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }

        if (gradeCard.isEmpty()) {
            logger.error("You haven't registered for any course");
        }

        return gradeCard;
    }

    /**
     * Handle API request for Making Payment
     * 
     * @param studentId:     ID of Student
     * @param modeOfPayment: Mode of Payment
     * @param cardNumber:    Card Number
     * @param cvv:           CVV of card
     * @return Success/Failure of Payment Request
     */
    @Path("/makePayment")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response makePayment(@QueryParam("studentId") int studentId, @QueryParam("modeOfPayment") int modeOfPayment,
            @QueryParam("cardNumber") String cardNumber, @QueryParam("cvv") String cvv) {

        double fee = 0.0;
        try {
            fee = registrationInterface.calculateFee(studentId);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return Response.status(500).entity("Some Error Occurred").build();
        }

        if (fee == 0.0) {
            return Response.status(403).entity("No pending fees").build();
        } else {
            try {
                notificationInterface.sendNotification(NotificationType.PAYMENT, studentId,
                        ModeOfPayment.getModeofPayment(modeOfPayment), fee, cardNumber, cvv);
            } catch (Exception e) {

                logger.error(e.getMessage());
                return Response.status(500).entity("Some Error Occurred").build();
            }
        }
        return Response.status(200).entity("Fees Paid").build();
    }

}