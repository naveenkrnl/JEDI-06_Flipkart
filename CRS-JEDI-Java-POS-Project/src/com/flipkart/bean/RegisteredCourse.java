package com.flipkart.bean;

import com.flipkart.constant.Grade;
import com.flipkart.dao.ProfessorDaoOperation;

/**
 *
 * Class for storing details of course Student has taken
 *
 */
public class RegisteredCourse {

	private int registeredCourseId;
	private int studentUserId;
	private int courseId;
	private Grade grade;

	public RegisteredCourse() {

	}

	public int getRegisteredCourseId() {
		return registeredCourseId;
	}

	public void setRegisteredCourseId(int registeredCourseId) {
		this.registeredCourseId = registeredCourseId;
	}

	public int getStudentUserId() {
		return studentUserId;
	}

	public void setStudentUserId(int studentUserId) {
		this.studentUserId = studentUserId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
