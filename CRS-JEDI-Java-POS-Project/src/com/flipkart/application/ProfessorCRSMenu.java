package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.constant.Grade;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * Class that display Professor Client Menu
 *
 */
public class ProfessorCRSMenu {
    private ProfessorCRSMenu() {

    }

    static Scanner scanner = new Scanner(System.in);

    static ProfessorInterface professorInterface = ProfessorOperation.getInstance();

    /**
     * Method to create Professor menu
     * 
     * @param professorUserId: professor id obtained after logging into the system
     *                         returns displays all the options for the professor,
     *                         and provides navigation
     */
    public static void createMenu(Professor professor) {
        // Display the options available for the Professor

        int input;
        while (CRSApplication.loggedin) {
            StringUtils.printMenu("Professor Access Menu",
                    new String[] { "View courses", "View Enrolled Students", "Add grade", "Logout" }, 100);

            StringUtils.printPrompt();

            // input user
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    // view all the courses taught by the professor
                    getCourses(professor.getUserId());
                    break;
                case 2:
                    // view all the enrolled students for the course
                    viewEnrolledStudents(professor.getUserId());
                    break;

                case 3:
                    // add grade for a student
                    addGrade(professor.getUserId());
                    break;
                case 4:
                    // logout from the system
                    CRSApplication.loggedin = false;
                    return;
                default:
                    StringUtils.printErrorMessage("***** Wrong Choice *****");
            }
        }
        scanner.close();
    }

    /**
     * Method to view enrolled Students in courses
     * 
     * @param professorUserId
     */
    private static void viewEnrolledStudents(int professorUserId) {
        StringUtils.printHeading("List of Enrolled Students");
        StringUtils.printTable(
                String.format("%20s %20s %20s %20s", "COURSE CODE", "COURSE CODE", "Student Email", "Student Name"));
        try {
            List<RegisteredCourse> enrolledStudents = professorInterface.viewEnrolledStudents(professorUserId);
            for (RegisteredCourse registeredCourse : enrolledStudents) {
                Course course = AdminDaoOperation.getInstance().getCouseFromCourseId(registeredCourse.getCourseId());
                Student student = StudentDaoOperation.getInstance()
                        .getStudentFromUserId(registeredCourse.getStudentUserId());
                StringUtils.printTable(String.format("%20s %20s %20s %20s", course.getCourseCode(),
                        course.getCourseName(), student.getEmail(), student.getName()));
            }
            StringUtils.printEndLine();
        } catch (Exception ex) {
            StringUtils.printErrorMessage(ex.getMessage() + "Something went wrong, please try again later!");
        }
    }

    /**
     * Method to get list of all Courses Professor has to teach
     * 
     * @param professorUserId
     */
    private static void getCourses(int professorUserId) {
        StringUtils.printHeading("List of All Courses taught by Professor");
        try {
            List<Course> registeredCourses = professorInterface.getCourses(professorUserId);
            viewCourseList(registeredCourses);
        } catch (Exception ex) {
            StringUtils.printErrorMessage("Something went wrong!" + ex.getMessage());
        }
    }

    /**
     * Method to help Professor grade a student
     * 
     * @param professorUserId
     */
    private static void addGrade(int professorUserId) {
        StringUtils.printHeading("Student Courses Data");

        try {
            List<RegisteredCourse> registeredCourses = professorInterface.viewEnrolledStudents(professorUserId);
            Map<Integer, Student> registeredStudentsMap = new HashMap<>();
            Map<Integer, Course> coursesMap = new HashMap<>();
            StringUtils.printTable(String.format("%20s %20s %20s %20s %20s", "COURSE CODE", "COURSE NAME",
                    "STUDENT EMAIL", "STUDENT NAME", "GRADE"));
            for (RegisteredCourse registeredCourse : registeredCourses) {
                Course course;
                if (coursesMap.containsKey(registeredCourse.getCourseId()))
                    course = coursesMap.get(registeredCourse.getCourseId());
                else {
                    course = AdminDaoOperation.getInstance().getCouseFromCourseId(registeredCourse.getCourseId());
                    coursesMap.put(registeredCourse.getCourseId(), course);
                }
                Student student;
                if (registeredStudentsMap.containsKey(registeredCourse.getStudentUserId()))
                    student = registeredStudentsMap.get(registeredCourse.getStudentUserId());
                else {
                    student = StudentDaoOperation.getInstance()
                            .getStudentFromUserId(registeredCourse.getStudentUserId());
                    registeredStudentsMap.put(student.getUserId(), student);
                }

                StringUtils.printTable(String.format("%20s %20s %20s %20s %20s", course.getCourseCode(),
                        course.getCourseName(), student.getEmail(), student.getName(), registeredCourse.getGrade()));
            }

            String email;
            String courseCode;
            String gradeStr;
            StringUtils.printHeading("Add Grade");
            System.out.println("Enter students email");
            email = scanner.next();
            System.out.println("Enter course code");
            courseCode = scanner.next();
            System.out.println("Enter grade[A-F]");
            gradeStr = scanner.next();
            if (Grade.valueOf(gradeStr) == null) {
                StringUtils.printErrorMessage("Grade Entered Invalid");
                return;
            }
            Grade grade = Grade.valueOf(gradeStr);

            Student selectedStudent = null;
            for (Student student : registeredStudentsMap.values()) {
                if (student.getEmail().equals(email)) {
                    selectedStudent = student;
                    break;
                }
            }
            if (selectedStudent == null) {
                StringUtils.printErrorMessage("Student Email " + email + " Invalid");
                return;
            }
            Course selectedCourse = null;
            for (Course course : coursesMap.values()) {
                if (course.getCourseCode().equals(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }
            if (selectedCourse == null) {
                StringUtils.printErrorMessage("Course Code " + courseCode + " Invalid");
                return;
            }
            if (professorInterface.addGrade(selectedStudent.getUserId(), selectedCourse.getCourseId(), grade)) {
                StringUtils.printSuccessMessage("Grade Added Sussessfully");
            } else {
                StringUtils.printErrorMessage("Grade Addition Failed");
            }
        } catch (Exception ex) {
            // GradeNotAddedException
            // StringUtils.printErrorMessage("Grade cannot be added for" +
            // ex.getStudentId());
        }
        // catch ( ex) {
        // //SQLException
        // StringUtils.printErrorMessage("Grade not added, SQL exception occured " +
        // ex.getMessage());
        // }
    }

    /**
     * View Registered Courses
     * 
     * @param studentUserId
     * @return List of Registered Courses
     */
    private static void viewCourseList(List<Course> courses) {
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
