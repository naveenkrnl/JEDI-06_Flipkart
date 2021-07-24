package com.flipkart.application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.GradeNotAddedException;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;

/**
 *
 * Class that display Professor Client Menu
 *
 */
public class ProfessorCRSMenu {


    ProfessorInterface professorInterface=ProfessorOperation.getInstance();

    /**
     * Method to create Professor menu
     * @param profId: professor id obtained after logging into the system
     * returns displays all the options for the professor, and provides navigation
     */
    public void createMenu(String profId)
    {
        //Display the options available for the Professor
        Scanner sc=new Scanner(System.in);

        int input;
        while(CRSApplication.loggedin)
        {
            System.out.println("*****************************");
            System.out.println("**********Professor Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Logout");
            System.out.println("*****************************");

            //input user
            input=sc.nextInt();
            switch(input)
            {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    break;
                case 2:
                    //view all the enrolled students for the course
                    viewEnrolledStudents(profId);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    //logout from the system
                    CRSApplication.loggedin=false;
                    return;
                default:
                    System.err.println("***** Wrong Choice *****");
            }
        }


    }

    /**
     * Method to view enrolled Students in courses
     * @param profId
     */
    public void viewEnrolledStudents(String profId)
    {
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));
        try
        {
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            for(EnrolledStudent obj: enrolledStudents)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
            }

        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage()+"Something went wrong, please try again later!");
        }
    }

    /**
     * Method to get list of all Courses Professor has to teach
     * @param profId
     */
    public void getCourses(String profId)
    {
        try
        {
            List<Course> coursesEnrolled=professorInterface.getCourses(profId);
            System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
            for(Course obj: coursesEnrolled)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
            }
        }
        catch(Exception ex)
        {
            System.err.println("Something went wrong!"+ex.getMessage());
        }
    }

    /**
     * Method to help Professor grade a student
     * @param profId
     */
    public void addGrade(String profId)
    {
        Scanner sc=new Scanner(System.in);

        int studentId;
        String courseCode,grade;
        try
        {
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","Student ID" ));
            for(EnrolledStudent obj: enrolledStudents)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
            }
            List<Course> coursesEnrolled=new ArrayList<Course>();
            coursesEnrolled	=professorInterface.getCourses(profId);
            System.out.println("----------------Add Grade--------------");
            System.out.println("Enter student id");
            studentId=sc.nextInt();
            System.out.println("Enter course code");
            courseCode=sc.next();
            System.out.println("Enter grade");
            grade=sc.next();
            if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))
            {
                professorInterface.addGrade(studentId, courseCode, grade);
                System.out.println("Grade added successfully for "+studentId);
            }
            else
            {
                System.out.println("Invalid data entered, try again!");
            }
        }
        catch(GradeNotAddedException ex)
        {
            System.err.println("Grade cannot be added for"+ex.getStudentId());

        }
        catch(SQLException ex)
        {
            System.err.println("Grade not added, SQL exception occured "+ex.getMessage());
        }

    }
}
