package com.flipkart.application;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.dao.NotificationDaoOperation;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.utils.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used as the main entry point of the application In main menu to
 * login, register are displayed
 */
public class CRSApplication {
    static boolean isLoggedIn = false;
    static final Scanner scanner = new Scanner(System.in);
    static final StudentInterface studentInterface = StudentOperation.getInstance();
    static AdminInterface adminInterface = AdminOperation.getInstance();
    static final ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    static final UserInterface userInterface = UserOperation.getInstance();
    static NotificationInterface notificationInterface = NotificationOperation.getInstance();

    private CRSApplication() {

    }

    public static void main(String[] args) {

        try {
            int userInput;
            // create the main menu
            createMainMenu();
            userInput = scanner.nextInt();
            // until user do not exit the application
            while (userInput != 4) {
                try {
                    switch (userInput) {
                        case 1:
                            // login
                            loginUser();
                            break;
                        case 2:
                            // student registration
                            registerStudent();
                            break;
                        case 3:
                            updatePassword();
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
                userInput = scanner.nextInt();
            }
        } catch (InputMismatchException ex) {
            StringUtils.printErrorMessage("Invalid Input entered.Please Retry");
        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something Went Wrong.Please Retry");
        }
        scanner.close();
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
    private static void loginUser() {
        // multiple exceptions are possible
        // invalid credential exception
        // user not found exception
        // user not approved exception

        try {
            StringUtils.printHeading("Login Portal", 100);
            System.out.println("Email:");
            String email = scanner.next();
            System.out.println("Password:");
            String password = scanner.next();
            isLoggedIn = userInterface.verifyCredentials(email, password);
            System.out.println(isLoggedIn);
            // 2 cases
            // true->role->student->approved
            if (isLoggedIn) {
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                LocalDateTime myDateObj = LocalDateTime.now();

                String formattedDate = myDateObj.format(myFormatObj);

                User user = userInterface.getUserFromEmail(email);
                switch (user.getRole()) {
                    case ADMIN:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful as Admin", 100);
                        AdminCRSMenu.createMenu();
                        break;
                    case PROFESSOR:
                        StringUtils.printSuccessMessage(formattedDate + " Login Successful for Professor");
                        Professor professor = professorInterface.getProfessorFromEmail(email);
                        ProfessorCRSMenu.createMenu(professor);

                        break;
                    case STUDENT:
                        Student student = studentInterface.getStudentFromEmail(email);
                        if (student.isApproved()) {
                            StringUtils.printSuccessMessage(formattedDate + " Login Successful for Student");
                            StudentCRSMenu.createMenu(student);
                        } else {
                            StringUtils.printErrorMessage(
                                    "Failed to login, you have not been approved by the administration!");
                            isLoggedIn = false;
                        }
                        break;
                }

            } else {
                StringUtils.printErrorMessage("Invalid Credentials!");
            }

        } catch (Exception ex) {
            // UserNotFoundException
            StringUtils.printErrorMessage(ex.getMessage());
        }
    }

    /**
     * Method to help Student register themselves, pending admin approval
     */
    private static void registerStudent() {
        try {
            // input all the student details
            StringUtils.printHeading("Student Registration Portal");
            System.out.println("Name:");
            String name = scanner.nextLine();

            System.out.println("Email:");
            String email = scanner.next();

            System.out.println("Password:");
            String password = scanner.next();

            System.out.println("Gender: \t 1: Male \t 2.Female\t 3.Other");
            int genderV = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Branch:");
            String branchName = scanner.nextLine();

            System.out.println("Batch:");
            int batch = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Address:");
            String address = scanner.nextLine();

            System.out.println("Country");
            String country = scanner.next();
            scanner.nextLine();

            Gender gender = Gender.getName(genderV);

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
            if (registrationStatus) {
                StringUtils.printSuccessMessage("Registration Successful");
            } else {
                StringUtils.printErrorMessage("Registration Failed");
            }

        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something went wrong! not registered. Please try again" + ex.getMessage());
        }
    }

    /**
     * Method to update password of User
     */
    private static void updatePassword() {
        try {
            StringUtils.printHeading("Update Password Portal");

            System.out.println("Email:");
            String email = scanner.next();

            System.out.println("Old Password:");
            String oldPassword = scanner.next();

            System.out.println("New Password:");
            String newPassword = scanner.next();

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
            StringUtils.printErrorMessage("Error Occurred " + ex.getMessage());
        }
    }
}