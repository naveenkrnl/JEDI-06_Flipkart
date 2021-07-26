/**
 * 
 */
package com.flipkart.exception;

/**
 * Exception thrown if the user id is already in use
 *
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;

	/**
	 * Constructor
	 *
	 * @param userId: User Id of User
	 */
	public UserIdAlreadyInUseException(String userId) {
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

	/**
	 * Setter for userId
	 *
	 * @param User Id for User
	 */
	public void setProfessorId(String userId) {
		this.userId = userId;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}
