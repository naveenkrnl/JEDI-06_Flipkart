package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.Grade;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;

import java.util.List;

public class ProfessorOperation implements ProfessorInterface {

	ProfessorDaoInterface professorDaoInterface = ProfessorDaoOperation.getInstance();
	static ProfessorOperation instance = null;

	ProfessorOperation() {

	}

	public static ProfessorOperation getInstance() {
		if (instance == null) {
			instance = new ProfessorOperation();
		}
		return instance;
	}

	@Override
	public Professor getProfessorFromEmail(String email) {
		Professor professor = professorDaoInterface.getProfessorFromEmail(email);
		if (professor == null) {
			// throw Prof not found
		}
		return professor;
	}

	@Override
	public boolean addGrade(int studentUserId, int courseId, Grade grade) {
		return professorDaoInterface.addGradeToStudent(studentUserId, courseId, grade);
	}

	@Override
	public List<RegisteredCourse> viewEnrolledStudents(int professorUserId) {
		return professorDaoInterface.getEnrolledStudents(professorUserId);
	}

	@Override
	public List<Course> getCourses(int professorUserId) {
		return professorDaoInterface.getCoursesByProfessorUserId(professorUserId);
	}

	@Override
	public Professor getProfessorById(int professorUserId) {
		return professorDaoInterface.getProfessorFromUserId(professorUserId);
	}
}
