package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.constant.Grade;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.dao.UserDaoInterface;

public class RegistrationOperation implements RegistrationInterface {

	RegistrationDaoInterface registrationDaoOperation = RegistrationDaoOperation.getInstance();
	StudentInterface studentInterface = StudentOperation.getInstance();
	AdminDaoInterface adminDaoInterface = AdminDaoOperation.getInstance();
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
			System.err.println("Usernotfound");
		}
		if (adminDaoInterface.getCouseFromCourseId(courseId) == null) {
			System.err.println("course not found");
		}
		if (registrationDaoOperation.numOfRegisteredCourses(studentUserId) >= 6) {
			System.err.println("course limit already rechead");
		}
		if (!registrationDaoOperation.seatAvailable(courseId)) {
			System.err.println("course limit rechead");
		}
		return registrationDaoOperation.registerStudentToCourse(courseId, studentUserId);
	}

	@Override
	public boolean dropCourse(int courseId, int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("Usernotfound");
		}
		if (adminDaoInterface.getCouseFromCourseId(courseId) == null) {
			System.err.println("course not found");
		}
		return registrationDaoOperation.dropCourseFromCourseIdAndStudentId(courseId, studentUserId);
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("Usernotfound");
		}
		return registrationDaoOperation.viewRegisteredCoursesForStudent(studentUserId);
	}

	@Override
	public List<Course> viewAvailableCoursesToStudent(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("Usernotfound");
		}
		return registrationDaoOperation.viewAvailableCoursesToStudent(studentUserId);
	}

	@Override
	public GradeCard getGradeCardFromStudentUserId(int studentUserId) {
		if (studentInterface.getStudentFromStudentUserId(studentUserId) == null) {
			System.err.println("Usernotfound");
		}
		return registrationDaoOperation.getGradeCardFromStudentUserId(studentUserId);
	}

	// @Override
	// public boolean addCourse(String courseCode, int studentId, List<Course>
	// availableCourseList) {

	// boolean isCoursePresent = false;
	// boolean ifSuccess;

	// for (Course course : availableCourseList) {
	// if (courseCode.equals(course.getCourseCode())) {
	// isCoursePresent = true;
	// }
	// }

	// if (!isCoursePresent) {
	// System.err.println("The selected course is not offered!");
	// return false;
	// }

	// try {
	// ifSuccess = registrationDaoOperation.addCourse(courseCode, studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return false;
	// }

	// return ifSuccess;
	// }

	// @Override
	// public boolean dropCourse(String courseCode, int studentId, List<Course>
	// registeredCourseList) {
	// boolean isCourseRegistered = false;
	// boolean ifSuccess;

	// for (Course course : registeredCourseList) {
	// if (courseCode.equals(course.getCourseCode())) {
	// isCourseRegistered = true;
	// }
	// }

	// if (!isCourseRegistered) {
	// System.err.println("The selected course is not registered!");
	// return false;
	// }

	// try {
	// ifSuccess = registrationDaoOperation.dropCourse(courseCode, studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return false;
	// }

	// return ifSuccess;
	// }

	// @Override
	// public double calculateFee(int studentId) {

	// double fee;

	// try {
	// fee = registrationDaoOperation.calculateFee(studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return 0.0;
	// }
	// return fee;
	// }

	// @Override
	// public List<GradeCard> viewGradeCard(int studentId) {

	// List<GradeCard> GradeCard = null;

	// try {
	// GradeCard = registrationDaoOperation.viewGradeCard(studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return null;
	// }

	// return GradeCard;
	// }

	// @Override
	// public List<Course> viewRegisteredCourses(int studentId) {

	// List<Course> registeredCourses = null;

	// try {
	// registeredCourses =
	// registrationDaoOperation.viewRegisteredCourses(studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return null;
	// }

	// return registeredCourses;
	// }

	// @Override
	// public boolean getRegistrationStatus(int studentId) {

	// boolean status = false;

	// try {
	// status = registrationDaoOperation.getRegistrationStatus(studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return false;
	// }
	// return status;
	// }

	// @Override
	// public void setRegistrationStatus(int studentId) {

	// try {
	// registrationDaoOperation.setRegistrationStatus(studentId);
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// return;
	// }
	// }

}
