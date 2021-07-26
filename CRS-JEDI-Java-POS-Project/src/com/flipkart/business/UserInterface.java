package com.flipkart.business;

import com.flipkart.constant.Role;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;

/**
 * 
 * 
 * Interface for User Operations
 *
 */
public interface UserInterface {

	/**
	 * Method to update password of a user
	 * 
	 * @param userID User ID
	 * @param newPassword New Password Of The User
	 * @return boolean indicating if the password is updated successfully
	 */
	boolean updatePassword(String userID, String newPassword);

	/**
	 * Method to verify User credentials
	 * 
	 * @param userID User ID
	 * @param password Password of the user
	 * @return boolean indicating if user exists in the database
	 * @throws UserNotFoundException If Student is not registered
	 */
	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException;

	/**
	 * Method to check if a user exists
	 *
	 * @param userID
	 * @return boolean indicating if user exists in the database
	 */
	public boolean checkExistence(String userID, String role);


	/**
	 * Method to get role of a specific User
	 * 
	 * @param userId User ID
	 * @return Role of the User
	 */
	public String getRole(String userId);

}
