package com.flipkart.business;

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
	 */
	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException;

	/**
	 * Method to get role of a specific User
	 * 
	 * @param userId User ID
	 * @return Role of the User
	 */
	public String getRole(String userId);

}
