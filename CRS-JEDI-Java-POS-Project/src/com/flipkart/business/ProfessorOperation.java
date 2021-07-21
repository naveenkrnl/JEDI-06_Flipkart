package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;

public class ProfessorOperation implements ProfessorInterface {

	private ProfessorOperation() {
	}

	public static ProfessorOperation getInstance() {
		System.out.println("Function getInstance called from ProfessorOperation");
		return null;
	}

	@Override
	public boolean addGrade(int studentId, String courseCode, String grade) {
		System.out.println("Function addGrade called from ProfessorOperation");
		return false;
	}

	@Override
	public List<RegisteredCourseStudent> viewEnrolledStudents(String profId) {
		System.out.println("Function viewEnrolledStudents called from ProfessorOperation");
		return null;
	}

	@Override
	public List<Course> getCourses(String profId) {
		System.out.println("Function getCourses called from ProfessorOperation");
		return null;
	}

	@Override
	public String getProfessorById(String profId) {
		System.out.println("Function getProfessorById called from ProfessorOperation");
		return null;
	}
}
