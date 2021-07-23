package com.flipkart.bean;

public class Course {

	private String courseCode;
	private String courseName;
	private int professorUserId;
	private int seats = 10;

	public Course() {

	}

	public Course(String courseCode, String courseName, int professorUserId) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.professorUserId = professorUserId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getProfessorUserId() {
		return professorUserId;
	}

	public void setProfessorUserId(int professorUserId) {
		this.professorUserId = professorUserId;
	}
}
