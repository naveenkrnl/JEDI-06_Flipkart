package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * Class for Student Validator
 */
public class StudentValidator {

	/**
	 * Method to validate if student is already registered for this particular
	 * course (courseCode) or not
	 *
	 * @param courseCode : Code of the Course
	 * @param studentId : ID Of the Student
	 * @param registeredCourseList : List Of registered Courses
	 * @return Student Registration Status
	 * @throws CourseNotFoundException Course Not Found Exception
	 */
	public static boolean isRegistered(String courseCode, int studentId, List<Course> registeredCourseList)
			throws CourseNotFoundException {
		for (Course course : registeredCourseList) {
			if (courseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Method to validate if courseCode is valid or not
	 *
	 * @param courseCode : Code Of the Course
	 * @param availableCourseList : List of available courses
	 * @return courseCode is valid or not
	 */
	public static boolean isValidCourseCode(String courseCode, List<Course> availableCourseList) {
		for (Course course : availableCourseList) {
			if (courseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true;
			}
		}

		return false;

	}

}
