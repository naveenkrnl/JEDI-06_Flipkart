package com.flipkart.exception;

public class UserNotFoundException extends Exception{

    private int userId;

    public UserNotFoundException(int userId){
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String getMessage(){
        return "User with userId: " + userId + " not found.";
    }
}
