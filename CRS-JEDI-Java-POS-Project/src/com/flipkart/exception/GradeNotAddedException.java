package com.flipkart.exception;

/**
 * Exception to check if student has been allotted grade by professor
 *
 * 
 */
public class GradeNotAddedException extends Exception {

	private final int studentId;

	/**
	 * Constructor
	 *
	 */
	public GradeNotAddedException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Getter function for studentId
	 *
	 */
	public int getStudentId() {
		return studentId;
	}

}
