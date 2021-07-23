package com.flipkart.bean;

/**
 *
 * Class for storing details of course Student has taken
 *
 */
public class RegisteredCourse {
	private String courseCode;
	private String courseName;
	private String rollNumber;

	public String getCourseCode() {
		System.out.println("Function getCourseCode called from RegisteredCourseStudent");
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		System.out.println("Function setCourseCode called from RegisteredCourseStudent");
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		System.out.println("Function getCourseName called from RegisteredCourseStudent");
		return courseName;
	}

	public void setCourseName(String courseName) {
		System.out.println("Function setCourseName called from RegisteredCourseStudent");
		this.courseName = courseName;
	}

	public String getrollNumber() {
		System.out.println("Function getrollNumber called from RegisteredCourseStudent");
		return rollNumber;
	}

	public void setrollNumber(String rollNumber) {
		System.out.println("Function setrollNumber called from RegisteredCourseStudent");
		this.rollNumber = rollNumber;
	}

	public RegisteredCourse(String courseCode, String courseName, String rollNumber) {
		super();
		System.out.println("Function RegisteredCourseStudent called from RegisteredCourseStudent");
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.rollNumber = rollNumber;
	}
}
