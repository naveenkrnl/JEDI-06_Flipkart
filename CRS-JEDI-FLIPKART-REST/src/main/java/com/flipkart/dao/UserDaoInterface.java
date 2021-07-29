package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

/**
 *
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {

	/**
	 * Method to verify credentials of Users from DataBase
	 * 
	 * @param userId   User Id
	 * @param password Password
	 * @return Returns true is User Credentials are verified, else false
	 * @throws UserNotFoundException If the given userid is not present in the
	 *                               database
	 */
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException;

	/**
	 * Method to update password of user in DataBase
	 * 
	 * @param userID User Id
	 * @return Returns true if User password is updated else returns false
	 */
	public boolean updatePassword(String userID);

	/**
	 * Method to get Role of User from DataBase
	 * 
	 * @param userId User Id
	 * @return Role
	 */
	public String getRole(String userId);

	/**
	 * Method to update password of user in DataBase
	 * 
	 * @param userID      User Id
	 * @param newPassword New password for the user
	 * @return Returns true if User password is updated else returns false
	 */
	public boolean updatePassword(String userID, String newPassword);
}
