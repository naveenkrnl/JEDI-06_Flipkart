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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public RegisteredCourseStudent(String courseCode, String courseName, int studentId) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
	}
}
