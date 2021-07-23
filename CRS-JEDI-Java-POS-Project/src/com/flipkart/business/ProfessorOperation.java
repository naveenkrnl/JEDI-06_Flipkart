package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;

public class ProfessorOperation implements ProfessorInterface {


	ProfessorDaoInterface professorDaoInterface = ProfessorDaoOperation.getInstance();
	 ProfessorOperation() {

	}

	public static ProfessorOperation getInstance() {
		return new ProfessorOperation();
	}

	@Override
	public boolean addGrade(int studentId, String courseCode, String grade) {
		this.professorDaoInterface.addGrade(studentId,courseCode,grade);
		return true;
	}

	@Override
	public List<RegisteredCourseStudent> viewEnrolledStudents(String profId) {
		return this.professorDaoInterface.getEnrolledStudents(profId);
	}

	@Override
	public List<Course> getCourses(String profId) {
		return this.professorDaoInterface.getCoursesByProfessor(profId);
	}

	@Override
	public String getProfessorById(String profId) {
		return this.professorDaoInterface.getProfessorById(profId);
	}
}
