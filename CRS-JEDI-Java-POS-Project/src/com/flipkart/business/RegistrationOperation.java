package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;

import java.util.List;

public class RegistrationOperation implements RegistrationInterface {

	final RegistrationDaoInterface registrationDaoOperation = RegistrationDaoOperation.getInstance();
	final StudentInterface studentInterface = StudentOperation.getInstance();
	final AdminDaoInterface adminDaoInterface = AdminDaoOperation.getInstance();
	static RegistrationOperation instance = null;

	private RegistrationOperation() {
	}

	public static RegistrationOperation getInstance() {
		if (instance == null) {
			instance = new RegistrationOperation();
		}
		return instance;
	}

	@Override
	public int numOfRegisteredCourses(int studentUserId) {
		return registrationDaoOperation.numOfRegisteredCourses(studentUserId);
	}

	@Override
	public boolean registerStudentToCourse(int courseId, int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("User Not found");
		}
		if (adminDaoInterface.getCourseFromCourseId(courseId) == null) {
			System.err.println("course not found");
		}
		if (registrationDaoOperation.numOfRegisteredCourses(studentUserId) >= 6) {
			System.err.println("course limit already reached");
		}
		if (!registrationDaoOperation.seatAvailable(courseId)) {
			System.err.println("course limit reached");
		}
		return registrationDaoOperation.registerStudentToCourse(courseId, studentUserId);
	}

	@Override
	public boolean dropCourse(int courseId, int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("User Not found");
		}
		if (adminDaoInterface.getCourseFromCourseId(courseId) == null) {
			System.err.println("course not found");
		}
		return registrationDaoOperation.dropCourseFromCourseIdAndStudentId(courseId, studentUserId);
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("User Not found");
		}
		return registrationDaoOperation.viewRegisteredCoursesForStudent(studentUserId);
	}

	@Override
	public List<Course> viewAvailableCoursesToStudent(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("User Not found");
		}
		return registrationDaoOperation.viewAvailableCoursesToStudent(studentUserId);
	}

	@Override
	public GradeCard getGradeCardFromStudentUserId(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("User Not found");
		}
		return registrationDaoOperation.getGradeCardFromStudentUserId(studentUserId);
	}
}
