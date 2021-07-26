/**
 *
 */
package com.flipkart.application;

import java.sql.SQLException;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
// import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.Color;
import com.flipkart.constant.Grade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.business.NotificationInterface;
import com.flipkart.business.NotificationOperation;
import com.flipkart.business.ProfessorInterface;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.RegistrationInterface;
import com.flipkart.business.RegistrationOperation;
import com.flipkart.business.StudentInterface;
import com.flipkart.business.StudentOperation;
import com.flipkart.utils.StringUtils;

/**
 *
 * The class displays the menu for student client
 *
 */
public class StudentCRSMenu {
    Scanner sc = new Scanner(System.in);
    RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
    ProfessorInterface professorInterface = ProfessorOperation.getInstance();
    NotificationInterface notificationInterface = NotificationOperation.getInstance();
    StudentInterface studentInterface = StudentOperation.getInstance();
    private boolean is_registered;

    /**
     * Method to generate Student Menu for course registration, addition, removal
     * and fee payment
     * 
     * @param studentUserId student id
     */
    public void create_menu(Student student) {
        System.out.println(student);
        int studentUserId = student.getUserId();
        while (CRSApplication.loggedin) {
            StringUtils.printMenu("Student Access Menu",
                    new String[] { "Course Registration", "Add Course", "Drop Course", "View Available Courses",
                            "View Registered Courses", "View grade card", "Make Payment", "Logout" },
                    100);

            StringUtils.printPrompt();
            int choice = sc.nextInt();

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
    }

    /**
     * Select course for registration
     * 
     * @param studentUserId
     */
    private void registerCourses(int studentUserId) {
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
                String courseCode = sc.next();
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
    private void addCourse(int studentUserId) {
        int count = registrationInterface.numOfRegisteredCourses(studentUserId);
        if (count >= 6) {
            StringUtils.printErrorMessage(" Course Limit Reached");
            return;
        }
        StringUtils.printHeading("Add Course Portal for Student");
        List<Course> availableCourseList = viewCourse(studentUserId);

        if (availableCourseList == null || availableCourseList.isEmpty()) {
            StringUtils.printErrorMessage("No courses are available for registration at this time");
            return;
        }

        try {
            System.out.println("Enter Course Code : ");
            String courseCode = sc.next();
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
    private void dropCourse(int studentUserId) {
        int count = registrationInterface.numOfRegisteredCourses(studentUserId);
        List<Course> registeredCourseList = viewRegisteredCourse(studentUserId);
        if (registeredCourseList == null || registeredCourseList.isEmpty() || count == 0) {
            StringUtils.printErrorMessage(" You have not been registeded in any courses");
            return;
        }
        StringUtils.printHeading("Drop Course Portal for Student");
        System.out.println("Enter the Course Code : ");
        String courseCode = sc.next();
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

    private List<Course> viewCourse(int studentUserId) {
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
            return null;
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
    private void viewCourseList(List<Course> courses) {
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

    private List<Course> viewRegisteredCourse(int studentUserId) {
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
    private void viewGradeCard(int studentUserId) {

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

    public int getScore(String grade) {
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
    private void make_payment(int studentUserId) {

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
