package com.flipkart.exception;

/**
 * Exception to check if user is approved by administration
 *
 * 
 */
public class UserNotApprovedException extends Exception {
	private final String userId;

	/**
	 * Constructor
	 *
	 */
	public UserNotApprovedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter for userId
	 *
	 */
	public String getUserId() {
		return userId;
	}

}
