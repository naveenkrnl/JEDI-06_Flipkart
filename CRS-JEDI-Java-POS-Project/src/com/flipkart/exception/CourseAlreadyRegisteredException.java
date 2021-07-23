package com.flipkart.exception;

public class CourseAlreadyRegisteredException extends Exception{

    private int courseCode;

    public CourseAlreadyRegisteredException(int courseCode)
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
    public String getMessage()
    {
        return "Course code:    " + courseCode + "is already registered.";
    }
}
