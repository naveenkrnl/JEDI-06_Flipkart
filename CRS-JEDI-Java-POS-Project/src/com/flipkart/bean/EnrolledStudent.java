package com.flipkart.bean;

/**
 * Class for storing details of course Student has taken
 */
public class EnrolledStudent {
	private String courseCode;
	private String courseName;
	private int studentId;

	/**
	 * Method to get Course Code
	 * 
	 * @return Course Code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Method to set Course Code
	 * 
	 * @param courseCode: course code
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * Method to get Course Name
	 * 
	 * @return Course Name: course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Method to set Course Name
	 * 
	 * @param courseName: course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Method to get Student Id of student enrolled in the course
	 * 
	 * @return Student Id: student id
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * Method to set Student Id of student enrolled in the course
	 * 
	 * @param studentId: student id
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Parameterized constructor
	 * 
	 * @param courseCode: course code
	 * @param courseName: Name of the course
	 * @param studentId: Id of the Student
	 */
	public EnrolledStudent(String courseCode, String courseName, int studentId) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.studentId = studentId;
	}
}
