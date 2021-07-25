package com.flipkart.exception;

public class AdminAccountNotCreatedException extends Exception{

    public AdminAccountNotCreatedException()
    {
    }

    @Override
    public String getMessage() {
        return "Administrative Account not created";
    }

}
