package com.flipkart.bean;

import java.util.List;

import com.flipkart.constant.Grade;

public class GradeCard {

	private List<RegisteredCourse> registeredCourses;
	private Student student;

	public List<RegisteredCourse> getRegisteredCourses() {
		return registeredCourses;
	}

	public void setRegisteredCourses(List<RegisteredCourse> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
