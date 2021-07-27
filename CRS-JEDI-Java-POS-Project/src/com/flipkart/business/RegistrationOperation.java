package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.exception.UserNotFoundException;

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
	public boolean registerStudentToCourse(int courseId, int studentUserId) throws UserNotFoundException,
			CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			throw new UserNotFoundException(studentUserId);
		}
		Course course = adminDaoInterface.getCourseFromCourseId(courseId);
		if (course == null) {
			throw new CourseNotFoundException(Integer.toString(courseId));
		}
		if (registrationDaoOperation.numOfRegisteredCourses(studentUserId) >= 6) {
			throw new CourseLimitExceedException(6);
		}
		if (!registrationDaoOperation.seatAvailable(courseId)) {
			throw new SeatNotAvailableException(course.getCourseCode());
		}
		return registrationDaoOperation.registerStudentToCourse(courseId, studentUserId);
	}

	@Override
	public boolean dropCourse(int courseId, int studentUserId) throws UserNotFoundException, CourseNotFoundException {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			throw new UserNotFoundException(studentUserId);
		}
		if (adminDaoInterface.getCourseFromCourseId(courseId) == null) {
			throw new CourseNotFoundException(Integer.toString(courseId));
		}
		return registrationDaoOperation.dropCourseFromCourseIdAndStudentId(courseId, studentUserId);
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentUserId) throws UserNotFoundException {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			throw new UserNotFoundException(studentUserId);
		}
		return registrationDaoOperation.viewRegisteredCoursesForStudent(studentUserId);
	}

	@Override
	public List<Course> viewAvailableCoursesToStudent(int studentUserId) throws UserNotFoundException {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			throw new UserNotFoundException(studentUserId);
		}
		return registrationDaoOperation.viewAvailableCoursesToStudent(studentUserId);
	}

	@Override
	public GradeCard getGradeCardFromStudentUserId(int studentUserId) throws UserNotFoundException {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			throw new UserNotFoundException(studentUserId);
		}
		return registrationDaoOperation.getGradeCardFromStudentUserId(studentUserId);
	}

	@Override
	public double calculateFeeFromStudentUserId(int studentUserId) {
		return registrationDaoOperation.calculateFeeFromStudentUserId(studentUserId);
	}
}
