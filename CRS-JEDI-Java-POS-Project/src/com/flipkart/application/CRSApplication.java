package com.flipkart.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constant.Color;
import com.flipkart.utils.StringUtils;
/**
 * This class is used as the main entry point of the application
 * In main menu to login, register are displayed
 */
public class CRSApplication {

    static boolean loggedin = false;
    StudentInterface studentInterface = StudentOperation.getInstance();
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication = new CRSApplication();
        int userInput;
        //create the main menu
        createMainMenu();
        userInput = sc.nextInt();
        try {

            //until user do not exit the application
            while (userInput != 4) {
                switch (userInput) {
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
                userInput = sc.nextInt();
            }
        } catch (Exception ex) {
            System.err.println("Error occured " + ex);
        } finally {
            sc.close();
        }
    }

    /**
     * Method to Create Main Menu
     */
    public static void createMainMenu() {
        StringUtils.printMenu("Welcome to Course Management System", new String[] {"Login","Student Registration" ,"Update password","Exit"},100);
        System.out.println( StringUtils.padding("Enter user input",100));
    }


    /**
     * Method for Login functionality
     */
    public void loginUser() {
        //multiple exceptions are possible
        //invalid credential exception
        //user not found exception
        //user not approved exception
        Scanner sc = new Scanner(System.in);
        String userId, password;
        try {
            System.out.println("-----------------Login------------------");
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            loggedin = userInterface.verifyCredentials(userId, password);
            //2 cases
            //true->role->student->approved
            if (loggedin) {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);


                //System.out.println("Welcome "+userId);
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                switch (userRole) {
                    case ADMIN:
                        System.out.println(formattedDate + " Login Successful");
                        AdminCRSMenu adminMenu = new AdminCRSMenu();
                        adminMenu.createMenu();
                        break;
                    case PROFESSOR:
                        System.out.println(formattedDate + " Login Successful");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        professorMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                            System.out.println(formattedDate + " Login Successful");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(studentId);

                        } else {
                            System.err.println("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }


            } else {
                System.err.println("Invalid Credentials!");
            }

        } catch (UserNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);

        String userId, name, password, address, country, branchName;
        Gender gender;
        int genderV, batch;
        try {
            //input all the student details
            System.out.println("---------------Student Registration-------------");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            userId = sc.next();
            System.out.println("Password:");
            password = sc.next();
            System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
            genderV = sc.nextInt();
            sc.nextLine();
            System.out.println("Branch:");
            branchName = sc.nextLine();
            System.out.println("Batch:");
            batch = sc.nextInt();
            sc.nextLine();
            System.out.println("Address:");
            address = sc.nextLine();
            System.out.println("Country");
            country = sc.next();
            gender = Gender.getName(genderV);
            int newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address, country);
            notificationInterface.sendNotification(NotificationType.REGISTRATION, newStudentId, null, 0);

        } catch (Exception ex) {
            System.err.println("Something went wrong! not registered. Please try again" + ex.getMessage());
        }
    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword;
        try {
            System.out.println("------------------Update Password--------------------");
            System.out.println("Email");
            userId = sc.next();
            System.out.println("New Password:");
            newPassword = sc.next();
            boolean isUpdated = userInterface.updatePassword(userId, newPassword);
            if (isUpdated)
                System.out.println("Password updated successfully!");

            else
                System.err.println("Something went wrong, please try again!");
        } catch (Exception ex) {
            System.err.println("Error Occured " + ex.getMessage());
        }
    }
}
