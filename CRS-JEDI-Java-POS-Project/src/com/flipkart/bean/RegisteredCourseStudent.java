package com.flipkart.bean;

/**
 *
 * Class for storing details of course Student has taken
 *
 */
public class RegisteredCourseStudent {
	private String courseCode;
	private String courseName;
	private int studentId;

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

	public int getStudentId() {
		System.out.println("Function getStudentId called from RegisteredCourseStudent");
		return studentId;
	}

	public void setStudentId(int studentId) {
		System.out.println("Function setStudentId called from RegisteredCourseStudent");
		this.studentId = studentId;
	}

	public RegisteredCourseStudent(String courseCode, String courseName, int studentId) {
		super();
		System.out.println("Function RegisteredCourseStudent called from RegisteredCourseStudent");
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
	}
}
