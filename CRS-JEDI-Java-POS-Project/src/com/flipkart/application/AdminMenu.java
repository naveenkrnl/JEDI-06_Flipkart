package com.flipkart.application;

import java.util.List;
import java.util.Scanner;


import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;

import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;


public class AdminMenu {


    AdminInterface adminOperation =AdminOperation.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface=NotificationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(){

        while(MainApplication.loggedin) {
            System.out.println("*****************************");
            System.out.println("**********Admin Menu*********");
            System.out.println("*****************************");
            System.out.println("1. View course in catalog");
            System.out.println("2. Add Course to catalog");
            System.out.println("3. Delete Course from catalog");
            System.out.println("4. Approve Students");
            System.out.println("5. View Pending Admission");
            System.out.println("6. Add Professor");
            System.out.println("7. Assign Courses To Professor");
            System.out.println("8. Logout");
            System.out.println("*****************************");

            int choice = scanner.nextInt();

            switch(choice) {
                case 1:
                    viewCoursesInCatalogue();
                    break;

                case 2:
                    addCourseToCatalogue();
                    break;

                case 3:
                    deleteCourse();
                    break;

                case 4:
                    approveStudent();
                    break;

                case 5:
                    viewPendingAdmissions();
                    break;

                case 6:
                    addProfessor();
                    break;

                case 7:
                    assignCourseToProfessor();
                    break;

                case 8:
                    MainApplication.loggedin = false;
                    return;

                default:
                    System.out.println("***** Wrong Choice *****");
            }
        }
    }


    private void assignCourseToProfessor() {
        System.out.println("AssignCourseToProfessor called");
    }


    private void addProfessor() {

        Professor professor = new Professor();

        System.out.println("Enter Professor Name:");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.println("Enter Designation:");
        String designation = scanner.next();
        professor.setDesignation(designation);

        System.out.println("Enter User Id:");
        String userId = scanner.next();
        professor.setUserId(userId);

        System.out.println("Enter Password:");
        String password = scanner.next();
        professor.setPassword(password);

        System.out.println("Enter Gender: \t 1: Male \t 2.Female \t 3.Other ");
        int gender = scanner.nextInt();
        professor.setGender(Gender.getName(gender));

        System.out.println("Enter Address:");
        String address = scanner.next();
        professor.setAddress(address);

        System.out.println("Enter Country:");
        String country = scanner.next();
        professor.setCountry(country);

        professor.setRole(Role.stringToName("Professor"));
    }

    private List<Student> viewPendingAdmissions() {
        System.out.println("viewPendingAdmission called");
    }


    private void approveStudent() {
        System.out.println("viewPendingAdmission called");
    }


    private void deleteCourse() {
        System.out.println("deleteCourse called");
    }


    private void addCourseToCatalogue() {
        System.out.println("addCourseToCatalogue called");
    }

    private void viewCoursesInCatalogue() {
        System.out.println("viewCoursesInCatalogue called");
    }
}