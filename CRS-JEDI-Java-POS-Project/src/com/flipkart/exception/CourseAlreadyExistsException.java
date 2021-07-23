package com.flipkart.exception;

public class CourseAlreadyExistsException extends Exception{

    private int courseCode;

    public CourseAlreadyExistsException(int courseCode)
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
        return "Course code:    " + courseCode + "already exists.";
    }
}
