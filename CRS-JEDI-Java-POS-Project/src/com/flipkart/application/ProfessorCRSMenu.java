package com.flipkart.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;

public class ProfessorCRSMenu {

    ProfessorInterface professorInterface=ProfessorOperation.getInstance();

    public void createMenu(String profId)
    {
        Scanner sc=new Scanner(System.in);

        int input;
        while(CRSApplication.loggedin)
        {
            System.out.println("*************************************");
            System.out.println("*********   Professor Menu   ********");

            System.out.println("*************************************");
            System.out.println("      1. View Courses");
            System.out.println("      2. View Enrolled Students");
            System.out.println("      3. Add grade");
            System.out.println("      4. Logout");
            System.out.println("*************************************");

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
                    System.out.println("***** Wrong Choice *****");
            }
        }


    }

    public void viewEnrolledStudents(String profId)
    {
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));
        try
        {
            List<RegisteredCourseStudent> enrolledStudents=new ArrayList<RegisteredCourseStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            for(RegisteredCourseStudent obj: enrolledStudents)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getrollNumber()));
            }

        }
        catch(Exception ex)
        {
            System.err.println(ex.getMessage()+"Something went wrong, please try again later!");
        }
    }

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

    public void addGrade(String profId)
    {
        Scanner sc=new Scanner(System.in);

        int studentId;
        String courseCode,grade;
        try
        {
            List<RegisteredCourseStudent> enrolledStudents=new ArrayList<RegisteredCourseStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId);
            System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","Student ID" ));
            for(RegisteredCourseStudent obj: enrolledStudents)
            {
                System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getrollNumber()));
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
        catch(Exception ex)
        {
            System.err.println("Grade cannot be added  - "+ex.getMessage());

        }

    }
}
