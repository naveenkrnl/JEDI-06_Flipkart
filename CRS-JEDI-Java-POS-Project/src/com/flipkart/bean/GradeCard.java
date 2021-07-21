package com.flipkart.bean;

import com.flipkart.constant.Grade;

public class GradeCard {
	

	private String courseCode;
	private String courseName;
	private Grade grade;

	public GradeCard(String courseCode, String courseName, Grade grade) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.setGrade(grade);
		System.out.println("Function setGrade called from StudentGrade");
	}

	public String getCourseCode() {
		System.out.println("Function getCourseCode called from StudentGrade");
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		System.out.println("Function setCourseCode called from StudentGrade");
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		System.out.println("Function getCourseName called from StudentGrade");
		return courseName;
	}

	public void setCourseName(String courseName) {
		System.out.println("Function setCourseName called from StudentGrade");
		this.courseName = courseName;
	}

	public Grade getGrade() {
		System.out.println("Function getGrade called from StudentGrade");
		return grade;
	}

	public void setGrade(Grade grade) {
		System.out.println("Function setGrade called from StudentGrade");
		this.grade = grade;
	}
}
