package com.flipkart.exception;

/**
 * Exception to check if user cannot be added
 *
 * 
 */
public class UserNotAddedException extends Exception {
	private final String userId;

	public UserNotAddedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter function for UserId
	 *
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "UserId: " + userId + " is already in use!";
	}
}
