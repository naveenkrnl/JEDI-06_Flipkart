package com.flipkart.exception;

/**
 * Exception to check if student has been allotted grade by professor
 *
 * 
 */
public class GradeNotAddedException extends Exception {

	private int studentId;

	/**
	 * Constructor
	 * 
	 * @param studentId
	 */
	public GradeNotAddedException(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Getter function for studentId
	 * 
	 * @return
	 */
	public int getStudentId() {
		return studentId;
	}

}
