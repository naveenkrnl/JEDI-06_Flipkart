package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;

/**
 * 
 * The Registration Operation provides the business logic for student
 * registration.
 * 
 */
public class RegistrationOperation implements RegistrationInterface {

	private static volatile RegistrationOperation instance = null;

	private RegistrationOperation() {
	}

	/**
	 * Method to make Registration Operation Singleton
	 * 
	 * @return RegistrationOperation Instance
	 */
	public static RegistrationOperation getInstance() {
		if (instance == null) {
			synchronized (RegistrationOperation.class) {
				instance = new RegistrationOperation();
			}
		}
		return instance;
	}

	RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperation.getInstance();

	/**
	 * Method to add Course selected by student
	 * 
	 * @param courseCode Course Code
	 * @param studentId Student ID
	 * @param availableCourseList List Of Available Courses
	 * @return boolean indicating if the course is added successfully
	 */
	@Override
	public boolean addCourse(String courseCode, int studentId, List<Course> availableCourseList)
			throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException {

		if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6) {
			throw new CourseLimitExceedException(6);
		} else if (registrationDaoInterface.isRegistered(courseCode, studentId)) {
			return false;
		} else if (!registrationDaoInterface.seatAvailable(courseCode)) {
			throw new SeatNotAvailableException(courseCode);
		} else if (!StudentValidator.isValidCourseCode(courseCode, availableCourseList)) {
			throw new CourseNotFoundException(courseCode);
		}

		return registrationDaoInterface.addCourse(courseCode, studentId);

	}

	/**
	 * Method to drop Course selected by student
	 * 
	 * @param courseCode Course Code
	 * @param studentId Student ID
	 * @param registeredCourseList List Of Registered Courses
	 * @return boolean indicating if the course is dropped successfully
	 */
	@Override
	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList)
			throws CourseNotFoundException, SQLException {
		if (!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList)) {
			throw new CourseNotFoundException(courseCode);
		}

		return registrationDaoInterface.dropCourse(courseCode, studentId);

	}

	/**
	 * Method for Fee Calculation for selected courses Fee calculation for selected
	 * courses
	 * 
	 * @param studentId Student ID
	 * @return Fee Student has to pay
	 */
	@Override
	public double calculateFee(int studentId) throws SQLException {
		return registrationDaoInterface.calculateFee(studentId);
	}

	/**
	 * Method to view grade card for students
	 * 
	 * @param studentId Student ID
	 * @return List of Student's Grades
	 */
	@Override
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException {
		return registrationDaoInterface.viewGradeCard(studentId);
	}

	/**
	 * Method to view the list of available courses
	 * 
	 * @param studentId Student ID
	 * @return List of courses
	 */
	@Override
	public List<Course> viewCourses(int studentId) throws SQLException {
		return registrationDaoInterface.viewCourses(studentId);
	}

	/**
	 * Method to view the list of courses registered by the student
	 * 
	 * @param studentId Student ID
	 * @return List of courses
	 */
	@Override
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException {
		return registrationDaoInterface.viewRegisteredCourses(studentId);
	}

	/**
	 * Method to check student registration status
	 * 
	 * @param studentId Student ID
	 * @return boolean indicating if the student's registration status
	 */
	@Override
	public boolean getRegistrationStatus(int studentId) throws SQLException {
		return registrationDaoInterface.getRegistrationStatus(studentId);
	}

	/**
	 * Method to set student registration status
	 * 
	 * @param studentId Student ID
	 */
	@Override
	public void setRegistrationStatus(int studentId) throws SQLException {
		registrationDaoInterface.setRegistrationStatus(studentId);

	}

}
