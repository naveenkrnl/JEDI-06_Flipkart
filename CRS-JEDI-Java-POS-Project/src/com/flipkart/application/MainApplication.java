package com.flipkart.application;

import com.flipkart.business.UserOperation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;



import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;


public class MainApplication {

    static boolean loggedin = false;
    StudentInterface studentInterface=StudentOperation.getInstance();
    UserInterface userInterface =UserOperation.getInstance();
    NotificationInterface notificationInterface=NotificationOperation.getInstance();

    public static void createMainMenu()
    {
        System.out.println("+++++Welcome to Course Management System+++++");
        System.out.println("1. Login");
        System.out.println("2. Student Registration");
        System.out.println("3. Update password");
        System.out.println("4. Exit");
        System.out.println("Enter user input");
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        MainApplication crsApplication=new MainApplication();
        int userInput;
        //create the main menu
        createMainMenu();
        userInput=sc.nextInt();
        try
        {

            //until user do not exit the application
            while(userInput!=4)
            {
                switch(userInput)
                {
                    case 1:
                        //login
                        crsApplication.loginUser();
                        break;
                    case 2:
                        //student registration
                        crsApplication.registerStudent();
                        break;
                    case 3:
                        crsApplication.updatePassword();
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
                createMainMenu();
                userInput=sc.nextInt();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Error occured "+ex);
        }
        finally
        {
            sc.close();
        }

    }


    public void loginUser()
    {

        Scanner sc=new Scanner(System.in);

        String userId,password;
        try
        {
            System.out.println("+++++++++Login+++++++++");
            System.out.println("Email:");
            userId=sc.next();
            System.out.println("Password:");
            password=sc.next();
            loggedin = userInterface.verifyCredentials(userId, password);
            //2 cases
            //true->role->student->approved
            if(loggedin)
            {
                String role=userInterface.getRole(userId);
                Role userRole=Role.stringToName(role);
                System.out.println(userRole);
                switch(userRole)
                {
                    case ADMIN:
                        System.out.println("Login Successful for admin");
                        AdminMenu adminMenu=new AdminMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                        System.out.println("Login Successful for professor");
                        ProfessorMenu professorMenu=new ProfessorMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId=studentInterface.getStudentId(userId);
                        boolean isApproved=studentInterface.isApproved(studentId);
                        if(isApproved)
                        {
                            System.out.println("Login Successful for student");
                            StudentMenu studentMenu=new StudentMenu();
                            studentMenu.create_menu(studentId);

                        }
                        else
                        {
                            System.out.println("Failed to login, you have not been approved by the administration!");
                            loggedin=false;
                        }
                        break;
                }


            }
            else
            {
                System.out.println("Invalid Credentials!");
            }

        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    public void registerStudent()
    {
        Scanner sc=new Scanner(System.in);

        String userId,name,password,address,country,branchName;
        Gender gender;
        int genderV, batch;
        //input all the student details
        System.out.println("++++++++Student Registration+++++++");
        System.out.println("Name:");
        name=sc.nextLine();
        System.out.println("Email:");
        userId=sc.next();
        System.out.println("Password:");
        password=sc.next();
        System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
        genderV=sc.nextInt();
        sc.nextLine();
        System.out.println("Branch:");
        branchName=sc.nextLine();
        System.out.println("Batch:");
        batch=sc.nextInt();
        sc.nextLine();
        System.out.println("Address:");
        address=sc.nextLine();
        System.out.println("Country");
        country=sc.next();
        gender=Gender.getName(genderV);
        System.out.println("++++++++Student Registration SuccessFull+++++++");

    }


    public void updatePassword()
    {
        Scanner sc=new Scanner(System.in);
        String userId,newPassword;
        System.out.println("+++++++++Update Password++++++++++");
        System.out.println("Email");
        userId=sc.next();
        System.out.println("New Password:");
        newPassword=sc.next();
        System.out.println("Password updated successfully!");
    }



}
