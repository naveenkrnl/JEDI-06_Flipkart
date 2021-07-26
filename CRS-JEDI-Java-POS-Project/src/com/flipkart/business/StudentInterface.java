package com.flipkart.business;

import com.flipkart.constant.Gender;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * 
 * 
 * Interface for Student Operations
 *
 */
public interface StudentInterface {

	/**
	 * Method to register a student, although student can't login until it's
	 * approved by admin
	 * 
	 * @param name Name Of the Student
	 * @param userID User ID
	 * @param password Password
	 * @param gender Gender
	 * @param batch Batch number
	 * @param branch Branch name
	 * @param address Address of the student
	 * @param country Country
	 * @return Student ID
	 */
	public int register(String name, String userID, String password, Gender gender, int batch, String branch,
			String address, String country) throws StudentNotRegisteredException;

	/**
	 * Method to get Student ID from User ID
	 * 
	 * @param userId User ID
	 * @return Student ID
	 */
	public int getStudentId(String userId);

	/**
	 * Method to check if student is approved by Admin or not
	 * 
	 * @param studentId Student ID
	 * @return boolean indicating if student is approved
	 */
	public boolean isApproved(int studentId);
}
