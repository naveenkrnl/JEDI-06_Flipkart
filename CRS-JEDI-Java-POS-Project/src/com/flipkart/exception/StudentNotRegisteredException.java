package com.flipkart.exception;

public class StudentNotRegisteredException extends Exception{

    private String studentname;

    public StudentNotRegisteredException(String studentname)
    {
        this.studentname = studentname;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    @Override
    public String getMessage(){
        return "Student : " + this.studentname + " is not registered.";
    }

}
