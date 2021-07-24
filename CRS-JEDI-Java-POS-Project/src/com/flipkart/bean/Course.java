package com.flipkart.bean;

import com.flipkart.dao.ProfessorDaoOperation;

public class Course {

	private int courseCode;
	private String courseName;
	private int professorUserId;
	private int courseCatalogId;

	public Course() {
		professorUserId = -1;
	}

	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getProfessorUserId() {
		return professorUserId;
	}

	public void setProfessorUserId(int professorUserId) {
		this.professorUserId = professorUserId;
	}

	public int getCourseCatalogId() {
		return courseCatalogId;
	}

	public void setCourseCatalogId(int courseCatalogId) {
		this.courseCatalogId = courseCatalogId;
	}

	public Professor getProfessor() {
		return ProfessorDaoOperation.getProfessorFromUserId(getProfessorUserId());
	}
}
