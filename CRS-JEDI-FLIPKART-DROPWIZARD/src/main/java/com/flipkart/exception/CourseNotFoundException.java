package com.flipkart.exception;

/**
 * Exception to check if course is available in catalog
 *
 * 
 */
public class CourseNotFoundException extends Exception {
	private String courseCode;

	public CourseNotFoundException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * 
	 * @return Course Code of Course
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " not found.";
	}
}
