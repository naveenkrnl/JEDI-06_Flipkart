package com.flipkart.exception;

public class SeatNotAvailableException extends Exception{

    private int courseCode;

    public SeatNotAvailableException(int courseCode)
    {
        this.courseCode = courseCode;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String getMessage() {
        return  "Seats are not available in : " + this.courseCode;
    }
}
