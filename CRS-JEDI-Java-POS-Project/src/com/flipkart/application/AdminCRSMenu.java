package com.flipkart.application;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Color;
import com.flipkart.constant.Gender;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.Role;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.business.AdminInterface;
import com.flipkart.business.AdminOperation;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.utils.StringUtils;

/**
 * Class that display Admin Client Menu
 */
public class AdminCRSMenu {

    AdminInterface adminOperation = AdminOperation.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface = NotificationOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu() {

        while (CRSApplication.loggedin) {

            StringUtils.printMenu("Administrative Control Menu",
                    new String[] { "View course in catalog", "Add Course to catalog", "Delete Course from catalog",
                            "Approve Students", "View Pending Admission", "Add Professor",
                            "Assign Courses To Professor", "Logout" },
                    100);

            StringUtils.printPrompt();

            int choice = scanner.nextInt();

            switch (choice) {
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
                    CRSApplication.loggedin = false;
                    return;

                default:
                    StringUtils.printErrorMessage("***** Wrong Choice *****");
            }
        }
    }

    /**
     * Method to assign Course to a Professor
     */
    private void assignCourseToProfessor() {
        List<Professor> professorList = adminOperation.viewProfessors();
        StringUtils.printHeading("List of Professors Available");
        StringUtils.printTable(String.format("%20s  %20s  %20s ", "ProfessorId", "Name", "Designation"));
        for (Professor professor : professorList) {
            StringUtils.printTable(String.format("%20s  %20s  %20s ", professor.getUserId(), professor.getName(),
                    professor.getDesignation()));
        }
        StringUtils.printEndLine();

        List<Course> courseList = adminOperation.viewCourses(1);
        StringUtils.printHeading("List of Courses Available");
        StringUtils.printTable(String.format("%20s  %20s", "CourseCode", "CourseName"));
        for (Course course : courseList) {
            StringUtils.printTable(String.format("%20s  %20s ", course.getCourseCode(), course.getCourseName()));
        }
        StringUtils.printEndLine();
        System.out.println("Enter Course Code of the course to be assigned:");
        String courseCode = scanner.next();

        System.out.println("Enter Professor's User Id:");
        String userId = scanner.next();

        try {

            adminOperation.assignCourse(courseCode, userId);

        } catch (CourseNotFoundException | UserNotFoundException e) {

            StringUtils.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Method to add Professor to DB
     */
    private void addProfessor() {

        Professor professor = new Professor();
        StringUtils.printHeading("Add Professor Portal");
        System.out.println("Enter Professor Name:");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.println("Enter Designation:");
        String designation = scanner.next();
        professor.setDesignation(designation);

        System.out.println("Enter Email:");
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

        try {
            adminOperation.addProfessor(professor);
        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            StringUtils.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Method to view Students who are yet to be approved
     * @return List of Students whose admissions are pending
     */
    private List<Student> viewPendingAdmissions() {

        List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();
        if (pendingStudentsList.size() == 0) {
            return pendingStudentsList;
        }
        StringUtils.printHeading("Students Pending for Approval");
        StringUtils.printTable(String.format("%20s  %20s  %20s  %20s", "UserId", "StudentId", "Name", "Gender"));
        for (Student student : pendingStudentsList) {
            StringUtils.printTable(String.format("%20s  %20d  %20s  %20s", student.getUserId(), student.getStudentId(),
                    student.getName(), student.getGender().toString()));
        }
        StringUtils.printEndLine();
        return pendingStudentsList;
    }

    /**
     * Method to approve a Student using Student's ID
     */
    private void approveStudent() {

        List<Student> studentList = viewPendingAdmissions();
        if (studentList.size() == 0) {
            return;
        }
        StringUtils.printHeading("Approve Student Portal");
        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();

        try {
            adminOperation.approveStudent(studentUserIdApproval, studentList);
            // send notification from system
            notificationInterface.sendNotification(NotificationType.REGISTRATION_APPROVAL, studentUserIdApproval, null,
                    0);

        } catch (StudentNotFoundForApprovalException e) {
            StringUtils.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Method to delete Course from catalogue
     *
     * @throws CourseNotFoundException
     */
    private void deleteCourse() {
        StringUtils.printHeading("Delete Course Portal");
        List<Course> courseList = viewCoursesInCatalogue();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();

        try {
            adminOperation.deleteCourse(courseCode, courseList);
        } catch (CourseNotFoundException | CourseNotDeletedException e) {
            StringUtils.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Method to add Course to catalogue
     */
    private void addCourseToCatalogue() {
        StringUtils.printHeading("Add Course to Catalogue Portal");
        List<Course> courseList = viewCoursesInCatalogue();
        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();

        Course course = new Course(courseCode, courseName, null, 10);

        try {
            adminOperation.addCourse(course, courseList);
        } catch (CourseFoundException e) {
            StringUtils.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Method to display courses in catalogue
     *
     * @return List of courses in catalogue
     */
    private List<Course> viewCoursesInCatalogue() {
        List<Course> courseList = adminOperation.viewCourses(1);
        if (courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        StringUtils.printHeading("Course Catalogue");
        StringUtils.printTable(String.format("%20s  %20s  %20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
        for (Course course : courseList) {
            String instructorId = "No Professor";
            if (course.getInstructorId() != null && !course.getInstructorId().isEmpty())
                instructorId = course.getInstructorId();
            StringUtils.printTable(
                    String.format("%20s  %20s  %20s", course.getCourseCode(), course.getCourseName(), instructorId));
        }
        StringUtils.printEndLine();
        return courseList;
    }
}