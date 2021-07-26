package com.flipkart.application;

import java.util.List;
import java.util.Scanner;

import com.flipkart.bean.Admin;
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
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.utils.StringUtils;

/**
 * Class that display Admin Client Menu
 */
public class AdminCRSMenu {

    AdminInterface adminOperation = AdminOperation.getInstance();
    Scanner scanner = new Scanner(System.in);
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();

    /**
     * Method to Create Admin Menu
     */
    public void createMenu(Admin admin) {

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
        List<Professor> professorList = adminOperation.viewProfessorList();
        if (professorList == null || professorList.isEmpty()) {
            StringUtils.printErrorMessage("No Professors in Portal");
            return;
        }
        StringUtils.printHeading("List of Professors Available");
        StringUtils.printTable(String.format("%20s  %20s  %20s ", "Email", "Name", "Designation"));
        for (Professor professor : professorList) {
            StringUtils.printTable(String.format("%20s  %20s  %20s ", professor.getEmail(), professor.getName(),
                    professor.getDesignation()));
        }
        StringUtils.printEndLine();

        List<Course> courseList = adminOperation.viewCourses(1);
        viewCourseList(courseList);
        System.out.println("Enter Course Code of the course to be assigned:");
        String courseCode = scanner.next();
        Course selectedCourse = null;
        for (Course course : courseList) {
            if (courseCode.equals(course.getCourseCode())) {
                selectedCourse = course;
                break;
            }
        }
        if (selectedCourse == null) {
            StringUtils.printErrorMessage("Course Code does not exist in protal");
            return;
        }

        System.out.println("Enter Professor's Email:");
        String professorEmail = scanner.next();
        Professor selectedProfessor = null;
        for (Professor professor : professorList) {
            if (professorEmail.equals(professor.getEmail())) {
                selectedProfessor = professor;
                break;
            }
        }
        if (selectedProfessor == null) {
            StringUtils.printErrorMessage("Professor's User Id does not exist in protal");
            return;
        }
        try {
            if (adminOperation.assignCourseToProfessor(selectedCourse.getCourseId(), selectedProfessor.getUserId())) {
                StringUtils.printSuccessMessage("Course has been Successfully Assigned");
            } else {
                StringUtils.printErrorMessage("Course has not been Assigned");
            }
        } catch (Exception e) {
            // CourseNotFoundException | UserNotFoundException
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
        String email = scanner.next();
        professor.setEmail(email);

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
        } catch (Exception e) {
            // UserIdAlreadyInUseException
            StringUtils.printErrorMessage(e.getMessage());
        }

    }

    /**
     * Method to view Students who are yet to be approved
     *
     * @return List of Students whose admissions are pending
     */
    private List<Student> viewPendingAdmissions() {

        List<Student> pendingStudentsList = adminOperation.viewPendingAdmissions();
        if (pendingStudentsList == null || pendingStudentsList.isEmpty()) {
            StringUtils.printErrorMessage("No Students Pending for Approval");
            return pendingStudentsList;
        }
        StringUtils.printHeading("Students Pending for Approval");
        StringUtils.printTable(String.format("%20s  %20s  %20s  %20s", "UserId", "StudentId", "Name", "Gender"));
        for (Student student : pendingStudentsList) {
            StringUtils.printTable(String.format("%20s  %20d  %20s  %20s", student.getUserId(), student.getUserId(),
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
        if (studentList == null || studentList.isEmpty()) {
            return;
        }
        StringUtils.printHeading("Approve Student Portal");
        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();
        Student selectStudent = null;
        for (Student student : studentList) {
            if (studentUserIdApproval == student.getUserId()) {
                selectStudent = student;
                break;
            }
        }
        if (selectStudent == null) {
            StringUtils.printErrorMessage("Selected Student Id " + studentUserIdApproval + " does not exist");
            return;
        }

        if (adminOperation.approveStudent(selectStudent.getUserId())) {
            StringUtils.printSuccessMessage("Student Approved Successfully");
        } else {
            StringUtils.printErrorMessage("Student Approval Failed");
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
        if (courseList == null || courseList.isEmpty()) {
            StringUtils.printErrorMessage("No Courses in the Portal");
            return;
        }
        System.out.println("Enter Course Code:");
        String courseCode = scanner.next();
        Course selectedCourse = null;
        for (Course course : courseList) {
            if (course.getCourseCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }
        if (selectedCourse == null) {
            StringUtils.printErrorMessage("No Such course Code Exists in the Portal");
            return;
        }

        if (adminOperation.deleteCourse(selectedCourse.getCourseId())) {
            StringUtils.printSuccessMessage("Course " + courseCode + " Deleted from Portal");
        } else {
            StringUtils.printErrorMessage("Course " + courseCode + " was not deleted from the Portal");
        }
    }

    /**
     * Method to add Course to catalogue
     */
    private void addCourseToCatalogue() {
        StringUtils.printHeading("Add Course to Catalogue Portal");
        List<Course> courseList = viewCoursesInCatalogue();
        Course course = new Course();
        course.setCourseCatalogId(1);
        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();
        course.setCourseCode(courseCode);

        System.out.println("Enter Course Name:");
        String courseName = scanner.nextLine();
        course.setCourseName(courseName);
        System.out.println(course);

        // System.out.println("Enter Course Fee:");
        // double courseFee = scanner.nextDouble();
        // scanner.nextLine();
        // course.setCourseFee(courseFee);

        try {
            if (adminOperation.addCourse(course)) {
                StringUtils.printSuccessMessage("Course " + course.getCourseName() + " Added successfully");
            } else {
                StringUtils.printErrorMessage("Something went wrong.Course Addition Failed");
            }
        } catch (Exception e) {
            // CourseFoundException
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
        if (courseList == null || courseList.isEmpty()) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        viewCourseList(courseList);
        return courseList;
    }

    /**
     * View Registered Courses
     * 
     * @param studentUserId
     * @return List of Registered Courses
     */
    private void viewCourseList(List<Course> courses) {
        StringUtils.printHeading("List of Courses");
        StringUtils.printTable(String.format("%-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
        for (Course course : courses) {
            String professorName = "No Professor";
            Professor professor = professorInterface.getProfessorById(course.getProfessorUserId());
            if (professor != null)
                professorName = professor.getName();

            StringUtils.printTable(
                    String.format("%-20s %-20s %-20s ", course.getCourseCode(), course.getCourseName(), professorName));
        }
        StringUtils.printEndLine();
    }
}
