package com.flipkart.bean;

import com.flipkart.constant.Grade;
import com.flipkart.dao.ProfessorDaoOperation;

/**
 *
 * Class for storing details of course Student has taken
 *
 */
public class RegisteredCourse {

	private Course course;
	private Grade grade;
	private Student student;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Professor getProfessor() {
		return ProfessorDaoOperation.getProfessorFromUserId(course.getProfessorUserId());
	}

}
