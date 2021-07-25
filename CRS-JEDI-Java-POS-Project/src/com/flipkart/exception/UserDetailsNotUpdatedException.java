package com.flipkart.exception;

public class UserDetailsNotUpdatedException extends Exception{
    private String email;

    public UserDetailsNotUpdatedException(String email) {
        this.email = email;
    }
    @Override
    public String getMessage() {
        return "Details of user " + email + " could not be updated";
    }
}
