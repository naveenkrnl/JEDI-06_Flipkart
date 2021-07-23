package com.flipkart.bean;

import com.flipkart.constant.Grade;

public class GradeCard {
	

	private String courseCode;
	private String courseName;
	private Grade grade;
	private int semester;

	public GradeCard(String courseCode, String courseName, Grade grade, int semester) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.semester = semester;
		this.setGrade(grade);
		System.out.println("Function setGrade called from GradeCard");
	}

	public String getCourseCode() {
		System.out.println("Function getCourseCode called from GradeCard");
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		System.out.println("Function setCourseCode called from GradeCard");
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		System.out.println("Function getCourseName called from GradeCard");
		return courseName;
	}

	public void setCourseName(String courseName) {
		System.out.println("Function setCourseName called from GradeCard");
		this.courseName = courseName;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getSemester() {
		return semester;
	}

	public Grade getGrade() {
		System.out.println("Function getGrade called from GradeCard");
		return grade;
	}

	public void setGrade(Grade grade) {
		System.out.println("Function setGrade called from GradeCard");
		this.grade = grade;
	}
}
