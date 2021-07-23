package com.flipkart.exception;

public class CourseNotFoundException extends Exception{

    private int courseCode;

    public CourseNotFoundException(int courseCode)
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
    public String getMessage(){
        return "Course:     " + courseCode + "not found.";
    }
}
