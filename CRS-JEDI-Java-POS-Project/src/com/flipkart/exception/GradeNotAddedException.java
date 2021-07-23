package com.flipkart.exception;

public class GradeNotAddedException extends Exception{

    private int studentId;

    public GradeNotAddedException(int studentId)
    {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getMessage(){
        return "Student:     " + studentId + "grade could not be added.";
    }
}

