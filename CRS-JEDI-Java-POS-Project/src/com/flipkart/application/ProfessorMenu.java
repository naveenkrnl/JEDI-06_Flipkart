package com.flipkart.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;

public class ProfessorMenu {

    ProfessorInterface professorInterface=ProfessorOperation.getInstance();

    public void createMenu(String profId)
    {
        Scanner sc=new Scanner(System.in);

        int input;
        while(MainApplication.loggedin)
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
                    viewRegisteredCourseStudents(profId);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    //logout from the system
                    MainApplication.loggedin=false;
                    return;
                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }


    }

    public void viewRegisteredCourseStudents(String profId)
    {
        System.out.println("View registered courses called");

    }

    public void getCourses(String profId)
    {
        System.out.println("Get courses called");
    }

    public void addGrade(String profId)
    {
        System.out.println("Add Grade called");
    }
}