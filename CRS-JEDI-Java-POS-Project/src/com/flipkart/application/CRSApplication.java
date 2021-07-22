package com.flipkart.application;

import com.flipkart.business.UserOperation;

import java.util.Scanner;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.UserInterface;
import com.flipkart.bean.Student;

public class CRSApplication {

    static boolean loggedin = false;
    StudentInterface studentInterface = StudentOperation.getInstance();
    UserInterface userInterface = UserOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    static StudentManagerCRS studentManager = new StudentManagerCRS();

    public static void createMainMenu() {
        // Format the content
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++ Hello, Welcome to Course Management System +++++ ");
        System.out.println("             1. Login                                  ");
        System.out.println("             2. Student Registration  (Sign Up)        ");
        System.out.println("             3. Student Updation                       ");
        System.out.println("             4. Student Deletion                       ");
        System.out.println("             5. Show All Students                      ");
        System.out.println("             6. Exit                                   ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("\nEnter user input\n");
    }

    public static void main(String[] args) {

        studentManager.createDummy();
        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication = new CRSApplication();
        int userInput;
        // create the main menu
        createMainMenu();
        userInput = sc.nextInt();
        try {

            // until user do not exit the application
            while (userInput != 6) {
                switch (userInput) {
                    case 1:
                        // login
                        crsApplication.loginStudent();
                        break;
                    case 2:
                        // student registration
                        crsApplication.registerStudent();
                        break;
                    case 3:
                        // student updation
                        crsApplication.updateStudentInfo();
                        break;
                    case 4:
                        // student deletion
                        crsApplication.deleteStudent();
                        break;
                    case 5:
                        crsApplication.listAllStudents();
                        break;
                    default:
                        System.out.println("Invalid Input");
                }
                createMainMenu();
                userInput = sc.nextInt();
            }
        } catch (Exception ex) {
            System.out.println("Error occured " + ex);
        } finally {
            sc.close();
        }

    }

    public void loginUser() {

        Scanner sc = new Scanner(System.in);

        String userId, password;
        try {
            System.out.println("+++++++++Login+++++++++");
            System.out.println("Email:");
            userId = sc.nextLine();
            System.out.println("Password:");
            password = sc.nextLine();
            loggedin = userInterface.verifyCredentials(userId, password);
            // 2 cases
            // true->role->student->approved
            if (loggedin) {
                String role = userInterface.getRole(userId);
                Role userRole = Role.stringToName(role);
                System.out.println(userRole);
                switch (userRole) {
                    case ADMIN:
                        System.out.println("Login Successful for admin");
                        AdminCRSMenu adminCRSMenu = new AdminCRSMenu();
                        adminCRSMenu.createMenu();
                        break;
                    case PROFESSOR:
                        System.out.println("Login Successful for professor");
                        ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu();
                        professorCRSMenu.createMenu(userId);

                        break;
                    case STUDENT:

                        int studentId = studentInterface.getStudentId(userId);
                        boolean isApproved = studentInterface.isApproved(studentId);
                        if (isApproved) {
                            System.out.println("Login Successful for student");
                            StudentCRSMenu studentCRSMenu = new StudentCRSMenu();
                            studentCRSMenu.create_menu(studentId);

                        } else {
                            System.out.println("Failed to login, you have not been approved by the administration!");
                            loggedin = false;
                        }
                        break;
                }

            } else {
                System.out.println("Invalid Credentials!");
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void registerStudent() {
        Scanner sc = new Scanner(System.in);

        String userId, name, password, address, country, branchName;
        Gender gender;
        int genderV, batch;
        // input all the student details
        System.out.println("++++++++Student Registration+++++++");
        System.out.println("Name:");
        name = sc.nextLine();
        System.out.println("Email:");
        userId = sc.nextLine();
        System.out.println("Password:");
        password = sc.nextLine();
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
        country = sc.nextLine();
        gender = Gender.getName(genderV);
        // Student student = new Student();
        boolean ifSuccess = studentManager.createStudent(userId, name, password, address, country, branchName, gender,
                batch);

        if (ifSuccess)
            System.out.println("++++++++Student Registration SuccessFull+++++++");
        else
            System.out.println("             Email Id already registered!!               ");
    }

    public Student loginStudent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter email id:");
        String emailID = sc.nextLine().trim();
        System.out.println("Enter password:");
        String password = sc.nextLine().trim();

        Student student = studentManager.getStudentfromemailID(emailID);

        int validateStatus = studentManager.validateStudent(emailID, password);

        if (validateStatus == -1) {
            System.out.println("Student does not exist.");
            return null;
        } else if (validateStatus == 0) {
            System.out.println("Wrong password entered.");
            return null;
        }
        System.out.println("                Student Login Successful                ");
        sc.nextLine();

        return student;
    }

    public void updateStudentInfo() {

        // String name, String address, String country
        Student student = loginStudent();
        if (student == null)
            return;
        Scanner sc = new Scanner(System.in);

        System.out.println("+++++++++Update Details (Press enter to skip)++++++++++");

        System.out.println("Enter new name (existing name: " + student.name + "):");
        String newName = sc.nextLine();

        System.out.println("Enter new address (existing address: " + student.address + "):");
        String newAddress = sc.nextLine();

        System.out.println("Enter new country (existing country: " + student.country + "):");
        String newCountry = sc.nextLine();

        studentManager.updateStudent(student.userId, newName, newAddress, newCountry);

        System.out.println("Details updated successfully!");
    }

    public void deleteStudent() {
        Student student = loginStudent();
        if (student == null)
            return;
        Scanner sc = new Scanner(System.in);

        studentManager.deleteStudent(student.userId);
        System.out.println("Student info deleted successfully!");
    }

    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        String userId, newPassword;
        System.out.println("+++++++++Update Password++++++++++");
        System.out.println("Email");
        userId = sc.nextLine();
        System.out.println("New Password:");
        newPassword = sc.nextLine();
        System.out.println("Password updated successfully!");
    }

    public void listAllStudents() {
        studentManager.listStudentInfo();
    }

}
