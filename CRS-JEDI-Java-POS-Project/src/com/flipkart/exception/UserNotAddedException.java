package com.flipkart.exception;

public class UserNotAddedException extends Exception{
    private int userId;

    public UserNotAddedException(int userId){
        this.userId = userId;
    }

    @Override
    public String getMessage(){
        return "User with userId: " + userId + "could not be added.";
    }
}
