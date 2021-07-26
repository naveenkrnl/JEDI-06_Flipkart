/**
 *
 */
package com.flipkart.application;

import com.flipkart.bean.*;
import com.flipkart.business.*;
import com.flipkart.constant.Color;
import com.flipkart.constant.Grade;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.utils.StringUtils;

import java.util.*;

/**
 *
 * The class displays the menu for student client
 *
 */
public class StudentCRSMenu {
    private StudentCRSMenu() {

    }

    static Scanner scanner = new Scanner(System.in);
    static RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    static ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    static NotificationInterface notificationInterface = NotificationOperation.getInstance();
    static StudentInterface studentInterface = StudentOperation.getInstance();

    /**
     * Method to generate Student Menu for course registration, addition, removal
     * and fee payment
     * 
     * @param studentUserId student id
     */
    public static void create_menu(Student student) {
        System.out.println(student);
        int studentUserId = student.getUserId();
        while (CRSApplication.loggedin) {
            StringUtils.printMenu("Student Access Menu",
                    new String[] { "Course Registration", "Add Course", "Drop Course", "View Available Courses",
                            "View Registered Courses", "View grade card", "Make Payment", "Logout" },
                    100);

            StringUtils.printPrompt();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerCourses(studentUserId);
                    break;

                case 2:
                    addCourse(studentUserId);
                    break;

                case 3:

                    dropCourse(studentUserId);
                    break;

                case 4:
                    viewCourse(studentUserId);
                    break;

                case 5:
                    viewRegisteredCourse(studentUserId);
                    break;

                case 6:
                    viewGradeCard(studentUserId);
                    break;

                case 7:
                    make_payment(studentUserId);
                    break;

                case 8:
                    CRSApplication.loggedin = false;
                    return;

                default:
                    StringUtils.printErrorMessage("***** Wrong Choice *****");
            }
        }
        scanner.close();
    }

    /**
     * Select course for registration
     * 
     * @param studentUserId
     */
    private static void registerCourses(int studentUserId) {
        int count = registrationInterface.numOfRegisteredCourses(studentUserId);
        if (count >= 6) {
            StringUtils.printErrorMessage(" Registration is already completed");
            return;
        }

        StringUtils.printHeading("Course Registration Portal");
        // boolean
        while (count < 6) {
            try {
                List<Course> courseList = viewCourse(studentUserId);
                Course selectedCourse = null;
                if (courseList == null || courseList.isEmpty())
                    return;

                System.out.println("Enter Course Code : " + (count + 1));
                System.out.println("Enter 0 to exit");
                String courseCode = scanner.next();
                if (courseCode.equals("0")) {
                    break;
                }
                for (Course course : courseList) {
                    if (course.getCourseCode().equals(courseCode)) {
                        selectedCourse = course;
                        break;
                    }
                }
                if (selectedCourse == null) {
                    System.err.println("No such Course Found : " + courseCode);
                    continue;
                }
                if (registrationInterface.registerStudentToCourse(selectedCourse.getCourseId(), studentUserId)) {
                    System.out.println(
                            Color.ANSI_GREEN + "Course " + courseCode + " registered sucessfully." + Color.ANSI_RESET);
                    count++;
                } else {
                    System.err.println(" Registration Failed for Course : " + courseCode);
                }
            } catch (Exception e) {
                // CourseNotFoundException | CourseLimitExceedException |
                // SeatNotAvailableException| SQLException
                StringUtils.printErrorMessage(e.getMessage());
            }
        }
        StringUtils.printSuccessMessage("Registration Successful");
    }

    /**
     * Add course for registration
     * 
     * @param studentUserId
     */
    private static void addCourse(int studentUserId) {
        int count = registrationInterface.numOfRegisteredCourses(studentUserId);
        if (count >= 6) {
            StringUtils.printErrorMessage(" Course Limit Reached");
            return;
        }
        StringUtils.printHeading("Add Course Portal for Student");
        List<Course> availableCourseList = viewCourse(studentUserId);

        if (availableCourseList.isEmpty()) {
            StringUtils.printErrorMessage("No courses are available for registration at this time");
            return;
        }

        try {
            System.out.println("Enter Course Code : ");
            String courseCode = scanner.next();
            Course selectedCourse = null;
            for (Course course : availableCourseList) {
                if (course.getCourseCode().equals(courseCode)) {
                    selectedCourse = course;
                    break;
                }
            }
            if (selectedCourse == null) {
                System.err.println("No such Course Found : " + courseCode);
                return;
            }
            if (registrationInterface.registerStudentToCourse(selectedCourse.getCourseId(), studentUserId)) {
                StringUtils.printSuccessMessage(" You have successfully registered for Course : " + courseCode);
            } else {
                StringUtils.printErrorMessage(" Registration for Course : " + courseCode + " failed");
            }
        } catch (Exception e) {
            // CourseNotFoundException | CourseLimitExceedException |
            // SeatNotAvailableException | SQLException
            StringUtils.printErrorMessage(e.getMessage());
        }
    }

    /**
     * Drop Course
     * 
     * @param studentUserId
     */
    private static void dropCourse(int studentUserId) {
        int count = registrationInterface.numOfRegisteredCourses(studentUserId);
        List<Course> registeredCourseList = viewRegisteredCourse(studentUserId);
        if (registeredCourseList == null || registeredCourseList.isEmpty() || count == 0) {
            StringUtils.printErrorMessage(" You have not been registeded in any courses");
            return;
        }
        StringUtils.printHeading("Drop Course Portal for Student");
        System.out.println("Enter the Course Code : ");
        String courseCode = scanner.next();
        Course selectedCourse = null;
        for (Course course : registeredCourseList) {
            if (course.getCourseCode().equals(courseCode)) {
                selectedCourse = course;
                break;
            }
        }
        if (selectedCourse == null) {
            System.err.println("No such Course Found : " + courseCode);
            return;
        }
        try {
            if (registrationInterface.dropCourse(selectedCourse.getCourseId(), studentUserId))
                StringUtils.printSuccessMessage("You have successfully dropped Course : " + courseCode);
            else
                StringUtils.printErrorMessage("You have not registered for course : " + courseCode);

        } catch (Exception e) {
            // CourseNotFoundException
            // StringUtils.printErrorMessage("You have not registered for course : " +
            // e.getCourseCode());
        }
        // catch (Exception e) {
        // // SQLException
        // StringUtils.printErrorMessage(e.getMessage());
        // }
    }

    private static List<Course> viewCourse(int studentUserId) {
        StringUtils.printHeading("List of Available Courses");
        List<Course> coursesAvailable = null;
        try {
            coursesAvailable = registrationInterface.viewAvailableCoursesToStudent(studentUserId);
        } catch (Exception e) {
            // SQLException
            StringUtils.printErrorMessage(e.getMessage());
        }

        if (coursesAvailable == null || coursesAvailable.isEmpty()) {
            StringUtils.printErrorMessage("NO COURSE AVAILABLE");
            return new ArrayList<>();
        }
        viewCourseList(coursesAvailable);
        return coursesAvailable;
    }

    /**
     * View Registered Courses
     * 
     * @param studentUserId
     * @return List of Registered Courses
     */
    private static void viewCourseList(List<Course> courses) {
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

    private static List<Course> viewRegisteredCourse(int studentUserId) {
        StringUtils.printHeading("List of Registered Courses");
        List<Course> coursesRegistered = null;
        try {
            coursesRegistered = registrationInterface.viewRegisteredCourses(studentUserId);
        } catch (Exception e) {
            // SQLException
            StringUtils.printErrorMessage(e.getMessage());
        }

        if (coursesRegistered == null || coursesRegistered.isEmpty()) {
            StringUtils.printErrorMessage("You haven't registered for any course");
            return coursesRegistered;
        }
        viewCourseList(coursesRegistered);
        return coursesRegistered;
    }

    /**
     * View grade card for particular student
     * 
     * @param studentUserId
     */
    private static void viewGradeCard(int studentUserId) {

        StringUtils.printHeading("GRADE CARD");
        GradeCard gradeCard = null;
        try {
            gradeCard = registrationInterface.getGradeCardFromStudentUserId(studentUserId);
        } catch (Exception e) {
            // SQLException
            StringUtils.printErrorMessage(e.getMessage());
        }

        if (gradeCard == null || gradeCard.getRegisteredCourses() == null
                || gradeCard.getRegisteredCourses().isEmpty()) {
            StringUtils.printErrorMessage("You haven't registered for any course");
            return;
        }

        StringUtils
                .printTable(String.format("%-20s %-20s %-20s %-20s", "COURSE CODE", "COURSE NAME", "GRADE", "SCORE"));
        List<RegisteredCourse> registeredCourses = gradeCard.getRegisteredCourses();
        List<RegisteredCourse> graded = new ArrayList<>();
        List<RegisteredCourse> unGraded = new ArrayList<>();
        for (RegisteredCourse RegisteredCourse : registeredCourses) {
            if (RegisteredCourse.getGrade() == null || RegisteredCourse.getGrade() == Grade.NA)
                unGraded.add(RegisteredCourse);
            else
                graded.add(RegisteredCourse);
        }
        double total_score = 0;
        if (!graded.isEmpty()) {
            StringUtils.printTable("Graded Courses : ");
            for (RegisteredCourse RegisteredCourse : graded) {
                // TODO : Replace with correct value after
                Course course = AdminDaoOperation.getInstance().getCouseFromCourseId(RegisteredCourse.getCourseId());
                StringUtils.printTable(
                        String.format("  %-20s %-20s %-20s %-20s", course.getCourseCode(), course.getCourseName(),
                                RegisteredCourse.getGrade(), getScore(RegisteredCourse.getGrade().toString())));
                total_score += getScore(RegisteredCourse.getGrade().toString());
            }
        }
        if (!unGraded.isEmpty()) {
            StringUtils.printTable("Grade Awaited : ");
            for (RegisteredCourse RegisteredCourse : unGraded) {
                Course course = AdminDaoOperation.getInstance().getCouseFromCourseId(RegisteredCourse.getCourseId());
                StringUtils.printTable(String.format("  %-20s %-20s %-20s %-20s", course.getCourseCode(),
                        course.getCourseName(), "NA", "NA"));
            }
        }
        if (!graded.isEmpty()) {
            StringUtils.printEndLine(false);
            StringUtils.printTable(
                    String.format("  %-20s %-20s %-20s %-20s", "", "", "CGPA", total_score / (double) graded.size()));
        }
        StringUtils.printEndLine();
    }

    private static Map<String, Integer> gradeStrToScore;

    static {
        gradeStrToScore = new HashMap<>();
        gradeStrToScore.put("A", 10);
        gradeStrToScore.put("B", 9);
        gradeStrToScore.put("C", 8);
        gradeStrToScore.put("D", 7);
        gradeStrToScore.put("E", 6);
        gradeStrToScore.put("F", 5);
        gradeStrToScore.put("NA", 0);
        gradeStrToScore.put("EX", 0);
    }

    public static int getScore(String grade) {
        if (gradeStrToScore.containsKey(grade))
            return gradeStrToScore.get(grade);
        return 0;
    }

    /**
     * Make Payment for selected courses. Student is provided with an option to pay
     * the fees and select the mode of payment.
     * 
     * @param studentUserId
     */
    private static void make_payment(int studentUserId) {
        System.out.println(studentUserId);
        // StringUtils.printHeading("Payment Portal");
        // double fee = 0.0;
        // try {
        // fee = registrationInterface.calculateFee(studentUserId);
        // } catch (Exception e) {
        // // SQLException
        // StringUtils.printErrorMessage(e.getMessage());
        // }

        // if (fee == 0.0) {
        // StringUtils.printErrorMessage("You have not registered for any courses yet");
        // } else {

        // System.out.println("Your total fee = " + fee);
        // System.out.println("Want to continue Fee Payment(y/n)");
        // String ch = sc.next();
        // if (ch.equals("y")) {
        // System.out.println("Select Mode of Payment:");

        // int index = 1;
        // for (ModeOfPayment mode : ModeOfPayment.values()) {
        // System.out.println(index + " " + mode);
        // index = index + 1;
        // }

        // ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());

        // if (mode == null)
        // StringUtils.printErrorMessage("Invalid Input");
        // else {
        // try {
        // notificationInterface.sendNotification(NotificationType.PAYMENT,
        // studentUserId, mode, fee);
        // } catch (Exception e) {

        // StringUtils.printErrorMessage(e.getMessage());
        // }
        // }
        // }

        // }

    }

}
