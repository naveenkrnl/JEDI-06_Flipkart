package com.flipkart.exception;

public class CourseNotDeletedException extends Exception{

    private int courseCode;

    public CourseNotDeletedException(int courseCode)
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
        return "Course:     " + courseCode + "not deleted.";
    }
}