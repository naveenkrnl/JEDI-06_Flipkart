package com.flipkart.exception;

/**
 * Exception course is deleted from catalog
 *
 * 
 */
public class CourseNotDeletedException extends Exception {
	private final String courseCode;

	public CourseNotDeletedException(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 *
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "Course with courseCode: " + courseCode + " can not be deleted.";
	}
}
