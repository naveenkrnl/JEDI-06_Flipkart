package com.flipkart.restController;

import com.flipkart.bean.Course;
import com.flipkart.bean.User;
import com.flipkart.bean.*;
import com.flipkart.business.*;
import com.flipkart.constant.Color;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

@Path("/student")
public class StudentRESTAPIController {

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
    NotificationInterface notificationInterface=NotificationOperation.getInstance();
//    private boolean is_registered;



    @Path("/login")
    @POST
    @Produces("text/plain")
    public Response loginUser(User user) {
        System.out.println(user.getUserId());
        System.out.println(user.getPassword());
        return Response.status(200).entity("Login successful").build();
    }

    @Path("/registerCourses")
    @POST
    @Produces("text/plain")
    public void registerCourses(@QueryParam("studentId") int studentId,
                                @QueryParam("courseIds") List<String> courseIds)
    {
        if (courseIds.size() != 6)
        {
            System.err.println("Please enter exactly 6 courses");
            return;
        }

        boolean is_registered = getRegistrationStatus(studentId);
        if(is_registered)
        {
            System.err.println(" Registration is already completed");
            return;
        }

        int count = 0;
        StringUtils.printHeading("Course Registration Portal");
        for (String courseId : courseIds)
        {
            List<Course> courseList = null;
            try
            {
                courseList = registrationInterface.viewCourses(studentId);
            }
            catch(SQLException e)
            {
                System.err.println("Course list not available");
            }

            try
            {
                if(registrationInterface.addCourse(courseId,studentId,courseList))
                {
                    System.out.println("Course " + courseId + " registered successfully.");
                    count++;
                }
                else
                {
                    System.err.println(" You have already registered for Course : " + courseId);
                }
            }
            catch(CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException e)
            {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Registration Successful");
//        is_registered = true;

        try
        {
            registrationInterface.setRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }

    }

    @Path("/addCourse")
    @POST
    @Produces("text/plain")
    public void addCourse(@QueryParam("studentId") int studentId, @QueryParam("courseId") String courseId)
    {
        boolean is_registered = getRegistrationStatus(studentId);
        if(is_registered)
        {
            List<Course> availableCourseList = null;
            try
            {
                availableCourseList = registrationInterface.viewCourses(studentId);
            }
            catch (SQLException e)
            {
                StringUtils.printErrorMessage(e.getMessage());
            }

            if(availableCourseList == null)
                return;

            try
            {
                if(registrationInterface.addCourse(courseId, studentId,availableCourseList))
                {
                    StringUtils.printSuccessMessage(" You have successfully registered for Course : " + courseId);
                }
                else
                {
                    StringUtils.printErrorMessage(" You have already registered for Course : " + courseId);
                }
            }
            catch(CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException e)
            {
                StringUtils.printErrorMessage(e.getMessage());
            }
        }
        else
        {
            StringUtils.printErrorMessage("Please complete registration for courses");
        }
    }

    @Path("/viewRegisteredCourse")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourse(@QueryParam("studentId") int studentId)
    {
        List<Course> coursesRegistered=null;
        try
        {
            coursesRegistered = registrationInterface.viewRegisteredCourses(studentId);
        }
        catch (SQLException e)
        {

            StringUtils.printErrorMessage(e.getMessage());
        }

        if(coursesRegistered.isEmpty())
        {
            StringUtils.printErrorMessage("You haven't registered for any course");
            return null;
        }

        return coursesRegistered;
    }

    @Path("/viewCourse")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(@QueryParam("studentId") int studentId) throws SQLException {
        return registrationInterface.viewCourses(studentId);
    }

    private boolean getRegistrationStatus(int studentId)
    {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Path("/viewGradeCard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGrade> viewGradeCard(@QueryParam("studentId") int studentId)
    {
        List<StudentGrade> gradeCard = null;
        try {
            gradeCard = registrationInterface.viewGradeCard(studentId);
        } catch (SQLException e) {

            StringUtils.printErrorMessage(e.getMessage());
        }

        if (gradeCard.isEmpty()) {
            StringUtils.printErrorMessage("You haven't registered for any course");
        }

        return gradeCard;
    }

    @Path("/makePayment")
    @POST
    @Produces("text/plain")
    public void makePayment(@QueryParam("studentId") int studentId,
                            @QueryParam("modeOfPayment") int modeOfPayment,
                            @QueryParam("cardNumber") String cardNumber,
                            @QueryParam("cvv") String cvv)
    {

        double fee = 0.0;
        try
        {
            fee = registrationInterface.calculateFee(studentId);
        }
        catch (SQLException e)
        {
            StringUtils.printErrorMessage(e.getMessage());
        }

        if(fee == 0.0)
        {
            StringUtils.printErrorMessage("You have not  registered for any courses yet");
        }
        else
        {
            try
            {
                notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, ModeOfPayment.getModeofPayment(modeOfPayment), fee, cardNumber, cvv);
            }
            catch (Exception e)
            {

                StringUtils.printErrorMessage(e.getMessage());
            }
        }
    }
}