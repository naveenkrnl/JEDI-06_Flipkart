package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.validator.AdminValidator;

import java.util.List;

public class AdminOperation implements AdminInterface {

	private static AdminOperation instance = null;
	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();

	public static AdminOperation getInstance() {
		if (instance == null) {
			instance = new AdminOperation();
		}
		return instance;
	}

	// TODO : Fix me
	@Override
	public void deleteCourse(String courseCode, List<Course> courseList) {
		System.out.println("Function deleteCourse called from AdminOperation");
	}

	// TODO : Fix me
	@Override
	public void addCourse(Course newCourse, List<Course> courseList) {

		if (AdminValidator.isValidNewCourse(newCourse, courseList)) {
			System.err.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	@Override
	public void approveStudent(int studentId, List<Student> unapprovedStudentList) {

		if (!AdminValidator.isValidUnapprovedStudent(studentId, unapprovedStudentList)) {
			System.err.println("studentId: " + studentId + " is already approvet/not-present!");
			// TODO : User is already approved
			return;
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void addProfessor(Professor professor) {

		try {
			adminDaoOperation.addProfessor(professor);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void assignCourse(String courseCode, String professorId) {

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}
}