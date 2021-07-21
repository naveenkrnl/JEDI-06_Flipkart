package com.flipkart.bean;

public class Course {

	private String courseCode;
	private String courseName;
	private String instructorId;
	private int seats = 10;

	public Course() {

	}

	public Course(String courseCode, String courseName, String instructorId, int seats) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.setInstructorId(instructorId);
		this.seats = seats;
	}

	public String getCourseCode() {
		System.out.println("Function getCourseCode called from Course");
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		System.out.println("Function setCourseCode called from Course");
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		System.out.println("Function getCourseName called from Course");
		return courseName;
	}

	public void setCourseName(String courseName) {
		System.out.println("Function setCourseName called from Course");
		this.courseName = courseName;
	}

	public int getSeats() {
		System.out.println("Function getSeats called from Course");
		return seats;
	}

	public void setSeats(int seats) {
		System.out.println("Function setSeats called from Course");
		this.seats = seats;
	}

	public String getInstructorId() {
		System.out.println("Function getInstructorId called from Course");
		return instructorId;
	}

	public void setInstructorId(String instructorId) {
		System.out.println("Function setInstructorId called from Course");
		this.instructorId = instructorId;
	}
}
