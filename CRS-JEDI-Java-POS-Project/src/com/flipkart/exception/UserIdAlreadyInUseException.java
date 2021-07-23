package com.flipkart.exception;

public class UserIdAlreadyInUseException extends Exception{

    private int userId;

    public UserIdAlreadyInUseException(int userId){
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
        return "UserId: " + userId + " already in use.";
    }
}
