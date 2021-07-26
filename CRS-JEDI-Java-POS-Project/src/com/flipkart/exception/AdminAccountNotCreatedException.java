package com.flipkart.exception;

/**
 * Exception to check if the creation of Admin account fails
 *
 *
 */
public class AdminAccountNotCreatedException extends Exception{

    /**
     * Empty Constructor
     *
     */
    public AdminAccountNotCreatedException()
    {
    }

    /**
     * Message returned when exception is thrown
     */
    @Override
    public String getMessage() {
        return "Administrative Account not created";
    }

}
