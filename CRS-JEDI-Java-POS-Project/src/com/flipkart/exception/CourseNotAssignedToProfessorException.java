package com.flipkart.exception;

/**
 * Exception course is not assigned to professor
 *
 * 
 */
public class CourseNotAssignedToProfessorException extends Exception {
	private String courseCode;
	private String professorId;

	public CourseNotAssignedToProfessorException(String courseCode, String professorId) {
		this.courseCode = courseCode;
		this.professorId = professorId;
	}

	/**
	 * Get courseCode
	 *
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * get Professor id
	 *
	 */
	public String getProfessorId() {
		return professorId;
	}

	/**
	 * set professor id
	 *
	 */
	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
	 * set course code
	 *
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "courseCode: " + courseCode + " OR professorId: " + professorId + " does not exist!";
	}
}
