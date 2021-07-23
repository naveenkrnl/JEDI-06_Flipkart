package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/**
 * 
 * Class for Admin Validator
 * 
 */
public class AdminValidator {

	public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
		// Is saying false if couse in courseList else true
		for (Course course : courseList) {
			if (newCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
		for (Course course : courseList) {
			if (dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValidUnapprovedStudent(int studentId, List<Student> unapprovedStudentList) {
		// says true if student id in student list
		for (Student student : unapprovedStudentList) {
			if (studentId == student.getUserId()) {
				return true;
			}
		}
		return false;
	}
}