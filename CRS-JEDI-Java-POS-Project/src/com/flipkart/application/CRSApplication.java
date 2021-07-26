package com.flipkart.application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
//import com.flipkart.exception.UserNotFoundException;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.business.UserOperation;
import com.flipkart.constant.Color;
import com.flipkart.utils.StringUtils;

/**
 * This class is used as the main entry point of the application In main menu to
 * login, register are displayed
 */
public class CRSApplication {

    static boolean loggedin = false;
    StudentInterface studentInterface = StudentOperation.getInstance();
    AdminInterface adminInterface = AdminOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    public static void main(String[] args) {
        CRSApplication crsApplication = new CRSApplication();
        Scanner sc = new Scanner(System.in);
        try {
            int userInput;
            // create the main menu
            createMainMenu();
            userInput = sc.nextInt();
            // until user do not exit the application
            while (userInput != 4) {
                try {
                    switch (userInput) {
                        case 1:
                            // login
                            crsApplication.loginUser();
                            break;
                        case 2:
                            // student registration
                            crsApplication.registerStudent();
                            break;
                        case 3:
                            crsApplication.updatePassword();
                            break;
                        default:
                            StringUtils.printErrorMessage("Invalid Input");
                    }
                } catch (InputMismatchException ex) {
                    StringUtils.printErrorMessage("Invalid Input entered.Please Retry");
                } catch (Exception ex) {
                    StringUtils.printErrorMessage("Something Went Wrong.Please Retry");
                }
                createMainMenu();
                userInput = sc.nextInt();
            }
        } catch (InputMismatchException ex) {
            StringUtils.printErrorMessage("Invalid Input entered.Please Retry");
        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something Went Wrong.Please Retry");
        }
    }

    /**
     * Method to Create Main Menu
     */
    public static void createMainMenu() {
        StringUtils.printMenu("Welcome to Course Management System",
                new String[] { "Login", "Student Registration", "Update password", "Exit" }, 100);
        StringUtils.printPrompt();
    }

    /**
     * Method for Login functionality
     */
    public void loginUser() {
        // multiple exceptions are possible
        // invalid credential exception
        // user not found exception
        // user not approved exception

        Scanner sc = new Scanner(System.in);
        try {
            String email, password;
            StringUtils.printHeading("Login Portal", 100);
            System.out.println("Email:");
            email = sc.next();
            System.out.println("Password:");
            password = sc.next();
            loggedin = userInterface.verifyCredentials(email, password);
            System.out.println(loggedin);
            // 2 cases
            // true->role->student->approved
            if (loggedin) {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);

                System.out.println("Welcome " + email);
                User user = userInterface.getUserFromEmail(email);
                switch (user.getRole()) {
                    case ADMIN:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful as Admin", 100);
                        AdminCRSMenu adminMenu = new AdminCRSMenu();
                        adminMenu.createMenu(adminInterface.getAdminFromUserId(user.getUserId()));
                        break;
                    case PROFESSOR:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful for Professor");
                        ProfessorCRSMenu professorMenu = new ProfessorCRSMenu();
                        Professor professor = professorInterface.getProfessorFromEmail(email);
                        professorMenu.createMenu(professor);

                        break;
                    case STUDENT:
                        Student student = studentInterface.getStudentFromEmail(email);
                        if (student.isApproved()) {
                            StringUtils.printSuccessMessage(formattedDate + " Login Successful for Student");
                            StudentCRSMenu studentMenu = new StudentCRSMenu();
                            studentMenu.create_menu(student);
                        } else {
                            StringUtils.printErrorMessage(
                                    "Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }

            } else {
                StringUtils.printErrorMessage("Invalid Credentials!");
            }

        } catch (Exception ex) {
            // UserNotFoundException
            StringUtils.printErrorMessage(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Method to help Student register themselves, pending admin approval
     */
    public void registerStudent() {
        Scanner sc = new Scanner(System.in);
        try {
            String email, name, password, address, country, branchName;
            Gender gender;
            int genderV, batch;
            // input all the student details
            StringUtils.printHeading("Student Registration Portal");
            System.out.println("Name:");
            name = sc.nextLine();
            System.out.println("Email:");
            email = sc.next();
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
            sc.nextLine();
            gender = Gender.getName(genderV);
            User user = userInterface.getUserFromEmail(email);
            if (user != null)
                throw new UserIdAlreadyInUseException(email);
            Student student = new Student();
            student.setName(name);
            student.setEmail(email);
            student.setPassword(password);
            student.setGender(gender);
            student.setBranchName(branchName);
            student.setBatch(batch);
            student.setAddress(address);
            student.setCountry(country);
            boolean registrationStatus = studentInterface.register(student);
            if (!registrationStatus)
                throw new Exception("Registration Failed");
            else
                System.out.println("REGISTRATION DONE");

            // int newStudentId = studentInterface.register(name, userId, password, gender,
            // batch, branchName, address,
            // country);
            // notificationInterface.sendNotification(NotificationType.REGISTRATION,
            // newStudentId, null, 0);

        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something went wrong! not registered. Please try again" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Method to update password of User
     */
    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        try {
            String email, newPassword, oldPassword;
            StringUtils.printHeading("Update Password Portal");
            System.out.println("Email:");
            email = sc.next();
            System.out.println("Old Password:");
            oldPassword = sc.next();
            System.out.println("New Password:");
            newPassword = sc.next();
            if (!userInterface.verifyCredentials(email, oldPassword)) {
                StringUtils.printErrorMessage("Invalid Old Credentials");
                return;
            }
            boolean isUpdated = userInterface.updatePassword(email, newPassword);
            if (isUpdated)
                StringUtils.printSuccessMessage("Password updated successfully!");
            else
                StringUtils.printErrorMessage("Something went wrong, please try again!");
        } catch (Exception ex) {
            StringUtils.printErrorMessage("Error Occured " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}