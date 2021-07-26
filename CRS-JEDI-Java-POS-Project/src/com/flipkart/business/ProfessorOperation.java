package com.flipkart.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.GradeNotAddedException;


public class ProfessorOperation implements ProfessorInterface {

	private static volatile ProfessorOperation instance = null;
	ProfessorDaoInterface professorDAOInterface = ProfessorDaoOperation.getInstance();

	private ProfessorOperation() {

	}

	public static ProfessorOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (ProfessorOperation.class) {
				instance = new ProfessorOperation();
			}
		}
		return instance;
	}

	@Override
	public boolean addGrade(int studentId, String courseCode, String grade) throws GradeNotAddedException {
		try {
			professorDAOInterface.addGrade(studentId, courseCode, grade);
		} catch (Exception ex) {
			throw new GradeNotAddedException(studentId);
		}
		return true;
	}

	@Override
	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException {
		List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
		try {
			enrolledStudents = professorDAOInterface.getEnrolledStudents(profId);
		} catch (Exception ex) {
			throw ex;
		}
		return enrolledStudents;
	}

	@Override
	public List<Course> getCourses(String profId) {
		List<Course> coursesOffered = new ArrayList<Course>();
		try {
			coursesOffered = professorDAOInterface.getCoursesByProfessor(profId);
		} catch (Exception ex) {
			throw ex;
		}
		return coursesOffered;
	}

	@Override
	public String getProfessorById(String profId) {
		return professorDAOInterface.getProfessorById(profId);
	}
}
