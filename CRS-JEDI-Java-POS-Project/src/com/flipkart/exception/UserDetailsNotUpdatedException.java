package com.flipkart.exception;

/**
 * Exception thrown if the updation of user details fails
 */
public class UserDetailsNotUpdatedException extends Exception{

    private String email;

    /**
     * Constructor
     *
     * @param courseCode: Course Code of Course
     */
    public UserDetailsNotUpdatedException(String email) {
        this.email = email;
    }

    /**
     * Getter method
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Details of user " + email + " could not be updated";
    }
}
