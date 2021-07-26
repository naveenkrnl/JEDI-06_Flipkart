package com.flipkart.exception;

/**
 * Exception to check if user is approved by administration
 *
 * 
 */
public class UserNotApprovedException extends Exception {
	private String userId;

	/**
	 * Constructor
	 * 
	 * @param userId: User Id of User
	 */
	public UserNotApprovedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter for userId
	 *
	 * @return User Id for User
	 */
	public String getUserId() {
		return userId;
	}

}
