package com.flipkart.application;

import java.util.Scanner;


import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;

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
                    viewRegisteredCourseStudents(profId);
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

    public void viewRegisteredCourseStudents(String profId)
    {
        System.out.println("View enrolled students called");

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