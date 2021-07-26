package com.flipkart.exception;

/**
 * Exception to check if user exists
 *
 * 
 */
public class UserNotFoundException extends Exception {

	private final String userId;

	/***
	 * Getter function for UserId
	 *
	 */
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return "User with userId: " + userId + " not found.";
	}

}
