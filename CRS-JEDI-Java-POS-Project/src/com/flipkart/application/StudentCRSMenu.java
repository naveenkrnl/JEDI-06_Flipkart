/**
 *
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.Color;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
import com.flipkart.utils.StringUtils;


/**
 *
 *  The class displays the menu for student client
 *
 */
public class StudentCRSMenu {
    Scanner sc = new Scanner(System.in);
    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface=NotificationOperation.getInstance();
    private boolean is_registered;

    /**
     * Method to generate Student Menu for course registration, addition, removal and fee payment
     *
     * @param studentId student id
     */
    public void create_menu(int studentId)
    {

        is_registered = getRegistrationStatus(studentId);
        while (CRSApplication.loggedin)
        {
            StringUtils.printMenu("Student Access Menu", new String[]{
                    "Register for Courses",
                    "Add New Course to Semester" ,
                    "Drop Course from Semester",
                    "View Available Courses",
                    "View Registered Courses",
                    "View Semester Grade Card" ,
                    "Pay Fees for Courses",
                    "Logout"
                            },100);

            StringUtils.printPrompt();

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    registerCourses(studentId);
                    break;

                case 2:
                    addCourse(studentId);
                    break;

                case 3:

                    dropCourse(studentId);
                    break;

                case 4:
                    viewCourse(studentId);
                    break;

                case 5:
                    viewRegisteredCourse(studentId);
                    break;

                case 6:
                    viewGradeCard(studentId);
                    break;

                case 7:
                    make_payment(studentId);
                    break;

                case 8:
                    CRSApplication.loggedin = false;
                    return;

                default:
                    StringUtils.printErrorMessage("***** Wrong Choice *****");
            }
        }
    }


    /**
     * Select course for registration
     * @param studentId student id
     * @param studentId
     * */
    private void registerCourses(int studentId)
    {
        if(is_registered)
        {
            StringUtils.printErrorMessage(" Registration is already completed");
            return;
        }

        int count = 0;
        StringUtils.printHeading("Course Registration Portal");
        while(count < 6)
        {
            try
            {
                List<Course> courseList=viewCourse(studentId);

                if(courseList==null)
                    return;

                System.out.println("Enter Course Code : " + (count+1));
                System.out.println("Enter 0 to exit");
                String courseCode = sc.next();

                if (courseCode.equals("0")){
                    break;
                }

                if(registrationInterface.addCourse(courseCode,studentId,courseList))
                {
                    System.out.println(Color.ANSI_GREEN + "Course " + courseCode + " registered sucessfully." + Color.ANSI_RESET);
                    count++;
                }
                else
                {
                    System.err.println(" You have already registered for Course : " + courseCode);
                }
            }
            catch(CourseNotFoundException | CourseLimitExceedException | SeatNotAvailableException | SQLException e)
            {
                StringUtils.printErrorMessage(e.getMessage());
            }
        }

        StringUtils.printSuccessMessage("Registration Successful");
        is_registered = true;

        try
        {
            registrationInterface.setRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
            StringUtils.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Add course for registration
     *
     * @param studentId
     */
    private void addCourse(int studentId)
    {
        if(is_registered)
        {
            StringUtils.printHeading("Add Course Portal for Student");

            List<Course> availableCourseList=viewCourse(studentId);

            if(availableCourseList==null)
                return;

            try
            {
                System.out.println("Enter Course Code : " );
                String courseCode = sc.next();
                if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
                {
                    StringUtils.printSuccessMessage(" You have successfully registered for Course : " + courseCode);
                }
                else
                {
                    StringUtils.printErrorMessage(" You have already registered for Course : " + courseCode);
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

    /**
     * Method to check if student is already registered
     *
     * @param studentId student id
     * @param studentId
     * @return Registration Status
     */
    private boolean getRegistrationStatus(int studentId)
    {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (SQLException e)
        {
            StringUtils.printErrorMessage(e.getMessage());
        }
        return false;
    }

    /**
     * Drop Course
     *
     * @param studentId student id
     * @param studentId
     */
    private void dropCourse(int studentId)
    {
        if(is_registered)
        {
            StringUtils.printHeading("Drop Course Portal for Student");
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null)
                return;

            System.out.println("Enter the Course Code : ");
            String courseCode = sc.next();

            try
            {
                registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
                StringUtils.printSuccessMessage("You have successfully dropped Course : " + courseCode);

            }
            catch(CourseNotFoundException e)
            {
                StringUtils.printErrorMessage("You have not registered for course : " + e.getCourseCode());
            }
            catch (SQLException e)
            {

                StringUtils.printErrorMessage(e.getMessage());
            }
        }
        else
        {
           StringUtils.printErrorMessage("Please complete registration for Courses");
        }
    }

    /**
     * View all available Courses
     *
     * @param studentId student id
     * @param studentId
     * @return List of available Courses
     */
    private List<Course> viewCourse(int studentId)
    {
        StringUtils.printHeading("List of Available Courses");
        List<Course> course_available=null;
        try
        {
            course_available = registrationInterface.viewCourses(studentId);
        }
        catch (SQLException e)
        {

           StringUtils.printErrorMessage(e.getMessage());
        }


        if(course_available.isEmpty())
        {
            StringUtils.printErrorMessage("NO COURSE AVAILABLE");
            return null;
        }


        StringUtils.printTable(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
        for(Course obj : course_available)
        {
            StringUtils.printTable(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
        }
        StringUtils.printEndLine();
        return course_available;
    }

    /**
     * View Registered Courses
     *
     * @param studentId student id
     * @param studentId
     * @return List of Registered Courses
     */
    private List<Course> viewRegisteredCourse(int studentId)
    {
        StringUtils.printHeading("List of Registered Courses");
        List<Course> course_registered=null;
        try
        {
            course_registered = registrationInterface.viewRegisteredCourses(studentId);
        }
        catch (SQLException e)
        {

            StringUtils.printErrorMessage(e.getMessage());
        }

        if(course_registered.isEmpty())
        {
           StringUtils.printErrorMessage("You haven't registered for any course");
            return null;
        }

       StringUtils.printTable(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

        for(Course obj : course_registered)
        {


           StringUtils.printTable(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),professorInterface.getProfessorById(obj.getInstructorId())));
        }
        StringUtils.printEndLine();
        return course_registered;
    }

    /**
     * View grade card for particular student
     *
     * @param studentId student id
     * @param studentId
     */
    private void viewGradeCard(int studentId) {

        StringUtils.printHeading("GRADE CARD");
        List<StudentGrade> grade_card = null;
        try {
            grade_card = registrationInterface.viewGradeCard(studentId);
        } catch (SQLException e) {

            StringUtils.printErrorMessage(e.getMessage());
        }

        if (grade_card.isEmpty()) {
            StringUtils.printErrorMessage("You haven't registered for any course");
            return;
        }

        StringUtils
                .printTable(String.format("%-20s %-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE", "SCORE"));
        List<StudentGrade> graded = new ArrayList<>();
        List<StudentGrade> unGraded = new ArrayList<>();
        for (StudentGrade studentGrade : grade_card) {
            if (studentGrade.getGrade() == null)
                unGraded.add(studentGrade);
            else
                graded.add(studentGrade);
        }
        double total_score = 0;
        if (!graded.isEmpty()) {
            StringUtils.printTable("Graded Courses : ");
            for (StudentGrade studentGrade : graded) {
                StringUtils.printTable(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                        studentGrade.getCourseName(), studentGrade.getGrade(), getScore(studentGrade.getGrade())));
                total_score += getScore(studentGrade.getGrade());
            }
        }
        if (!unGraded.isEmpty()) {
            StringUtils.printTable("Grade Awaited : ");
            for (StudentGrade studentGrade : unGraded) {
                StringUtils.printTable(String.format("  %-20s %-20s %-20s %-20s", studentGrade.getCourseCode(),
                        studentGrade.getCourseName(), "NA", "NA"));
            }
        }
    }

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
    /**
     * Make Payment for selected courses.
     * Student is provided with an option to pay the fees and select the mode of payment.
     *
     * @param studentId
     */
    private void make_payment(int studentId)
    {

        StringUtils.printHeading("Payment Portal");
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
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

            System.out.println("Your total fee  = " + fee);
            System.out.println("Want to continue Fee Payment(y/n)");
            String ch = sc.next();
            if(ch.equals("y"))
            {
                System.out.println("Select Mode of Payment:");

                int index = 1;
                for(ModeOfPayment mode : ModeOfPayment.values())
                {
                    System.out.println(index + " " + mode);
                    index = index + 1;
                }

                ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());
                String temp = sc.nextLine();
                if(mode == null)
                    StringUtils.printErrorMessage("Invalid Input");
                else
                {
                    System.out.println("Please Enter The 16 digit Card Number:");
                    String cardNumber = sc.nextLine();

                    System.out.println("Please Enter your CVV Number");
                    String cvv = sc.nextLine();

                    try
                    {
                        notificationInterface.sendNotification(NotificationType.PAYMENT, studentId, mode, fee, cardNumber, cvv);
                    }
                    catch (Exception e)
                    {

                        StringUtils.printErrorMessage(e.getMessage());
                    }
                }

            }

        }

    }
}


