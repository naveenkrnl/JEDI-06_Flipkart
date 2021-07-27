package com.flipkart.exception;

/**
 * Exception to check if user exists
 *
 * 
 */
public class UserNotFoundException extends Exception {

	private String email = "";
	private int userId = 0;

	/***
	 * Getter function for UserId
	 *
	 */
	public UserNotFoundException(String email) {
		this.email = email;
	}

	public UserNotFoundException(int userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		if (email != null && !email.isEmpty())
			return "User with email: " + email + " not found.";
		else
			return "User with userId: " + userId + " not found.";
	}

}
