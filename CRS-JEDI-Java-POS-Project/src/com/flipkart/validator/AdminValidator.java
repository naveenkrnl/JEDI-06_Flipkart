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
		for(Course course : courseList) {
			if(newCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}
	

	public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
		for(Course course : courseList) {
			if(dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true; 
			}
		}
		return false;
	}

	public static boolean isValidUnapprovedStudent(String studentId, List<Student> studentList) {
		for(Student student : studentList) {
			if(studentId == student.getUserId()) {
				return true;
			}
		}
		return false;
	}
}