package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.Grade;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDaoOperation implements RegistrationDaoInterface {

	private static RegistrationDaoOperation instance = null;
	private static final AdminDaoInterface adminDaoInterface = AdminDaoOperation.getInstance();
	final StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
	static Logger logger = Logger.getLogger("");

	private RegistrationDaoOperation() {
	}

	public static RegistrationDaoOperation getInstance() {
		if (instance == null) {
			instance = new RegistrationDaoOperation();
		}
		return instance;
	}

	@Override
	public boolean createRegisteredCourseDBRecordAndUpdateObject(RegisteredCourse registeredCourse) {
		Course course = adminDaoInterface.getCourseFromCourseId(registeredCourse.getCourseId());
		if (course == null) {
			// TODO : Throw Course Not Found
			return false;
		}

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_REGISTERED_COURSE;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
			// studentUserId, courseId, grade
			preparedStatement.setInt(1, registeredCourse.getStudentUserId());
			preparedStatement.setInt(2, registeredCourse.getCourseId());
			preparedStatement.setString(3, Grade.NA.toString());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				return false;
				// TODO : Add exception Course Registration failed
			}
			RegisteredCourse registeredCourseFromDB = getRegisteredCourseFromStudentIdAndCourseId(
					registeredCourse.getStudentUserId(), registeredCourse.getCourseId());
			assert registeredCourseFromDB != null;
			registeredCourse.setCourseId(registeredCourseFromDB.getCourseId());
			return true;
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return false;
	}

	@Override
	public RegisteredCourse getRegisteredCourseFromStudentIdAndCourseId(int studentUserId, int courseId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_REGISTERED_COURSE_FROM_STUDENT_ID_AND_COURSE_ID;

		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, studentUserId);
			preparedStatement.setInt(2, courseId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}

			// registeredCourseId, studentUserId, courseId, grade
			int registeredCourseId = resultSet.getInt(1);
			// int studentUserId = resultSet.getInt(2);
			// int courseId = resultSet.getInt(3);
			Grade grade = Grade.valueOf(resultSet.getString(4));

			RegisteredCourse registeredCourse = new RegisteredCourse();
			registeredCourse.setRegisteredCourseId(registeredCourseId);
			registeredCourse.setStudentUserId(studentUserId);
			registeredCourse.setCourseId(courseId);
			registeredCourse.setGrade(grade);
			return registeredCourse;

		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return null;
	}

	@Override
	public RegisteredCourse getRegisteredCourseFromRegisteredCourseId(int registeredCourseId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_REGISTERED_COURSE_FROM_REGISTERED_COURSE_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, registeredCourseId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}

			// registeredCourseId, studentUserId, courseId, grade
			// int registeredCourseId = resultSet.getInt(1);
			int studentUserId = resultSet.getInt(2);
			int courseId = resultSet.getInt(3);
			Grade grade = Grade.valueOf(resultSet.getString(4));

			RegisteredCourse registeredCourse = new RegisteredCourse();
			registeredCourse.setRegisteredCourseId(registeredCourseId);
			registeredCourse.setStudentUserId(studentUserId);
			registeredCourse.setCourseId(courseId);
			registeredCourse.setGrade(grade);
			return registeredCourse;

		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return null;
	}

	@Override
	public boolean registerStudentToCourse(int courseId, int studentUserId) {
		Course course = adminDaoInterface.getCourseFromCourseId(courseId);
		if (course == null) {
			return false;
			// TODO : Throw course not found exception
		}
		RegisteredCourse registeredCourse = new RegisteredCourse();
		registeredCourse.setCourseId(courseId);
		registeredCourse.setGrade(Grade.NA);
		registeredCourse.setStudentUserId(studentUserId);
		return createRegisteredCourseDBRecordAndUpdateObject(registeredCourse);
	}

	@Override
	public int numOfRegisteredCourses(int studentUserId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.NUMBER_OF_REGISTERED_COURSES_FROM_STUDENT_USER_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, studentUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return 0;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return resultSet.getInt(1);
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return -1;
	}

	@Override
	public boolean seatAvailable(int courseId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.NUMBER_OF_STUDENTS_REGISTERED_FROM_COURSE_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, courseId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return true;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return resultSet.getInt(1) < 10;
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return false;

	}

	@Override
	public boolean isStudentAlreadyRegisteredToCourseId(int courseId, int studentUserId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.IS_STUDENT_ALREADY_REGISTERED_TO_COURSE_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, studentUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return false;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return resultSet.getInt(1) == 0;
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return false;
	}

	@Override
	public boolean dropCourseFromCourseIdAndStudentId(int courseId, int studentUserId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.DROP_COURSE_FROM_COURSE_ID_AND_STUDENT_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, courseId);
			preparedStatement.setInt(2, studentUserId);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				return false;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return true;
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return false;

	}

	@Override
	public double calculateFeeFromStudentUserId(int studentUserId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.CALCULATE_FEES_FROM_STUDENT_ID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {

			preparedStatement.setInt(1, studentUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return 0;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return resultSet.getDouble(1);
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return 0;
	}

	@Override
	public GradeCard getGradeCardFromStudentUserId(int studentUserId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_REGISTERED_COURSES_FROM_STUDENT_USER_ID;
		List<RegisteredCourse> registeredCourses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
			preparedStatement.setInt(1, studentUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int registeredCourseId = resultSet.getInt(1);
				// int studentUserId = resultSet.getInt(2);
				int courseId = resultSet.getInt(3);
				Grade grade = Grade.valueOf(resultSet.getString(4));
				RegisteredCourse registeredCourse = new RegisteredCourse();
				registeredCourse.setRegisteredCourseId(registeredCourseId);
				registeredCourse.setStudentUserId(studentUserId);
				registeredCourse.setCourseId(courseId);
				registeredCourse.setGrade(grade);
				registeredCourses.add(registeredCourse);
			}
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		GradeCard gradeCard = new GradeCard();
		gradeCard.setRegisteredCourses(registeredCourses);
		gradeCard.setStudent(studentDaoInterface.getStudentFromUserId(studentUserId));
		return gradeCard;
	}

	@Override
	public List<Course> viewAvailableCoursesToStudent(int studentUserId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_COURSES_AVAILABLE_TO_STUDENT_USER_ID;
		List<Course> availableCourses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
			preparedStatement.setInt(1, studentUserId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course = new Course();
				// courseId, courseCode, courseName, professorUserId, courseCatalogId
				course.setCourseId(resultSet.getInt(1));
				course.setCourseCode(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				course.setProfessorUserId(resultSet.getInt(4));
				course.setCourseCatalogId(resultSet.getInt(5));
				course.setCourseFee(resultSet.getDouble(6));
				availableCourses.add(course);
			}
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return availableCourses;
	}

	@Override
	public List<Course> viewRegisteredCoursesForStudent(int studentUserId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_ALL_COURSES_REGISTERED_BY_STUDENT_USER_ID;
		List<Course> registeredCourses = new ArrayList<>();
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
			preparedStatement.setInt(1, studentUserId);
			System.err.println(preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Course course = new Course();
				// courseId, courseCode, courseName, professorUserId, courseCatalogId
				course.setCourseId(resultSet.getInt(1));
				course.setCourseCode(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				course.setProfessorUserId(resultSet.getInt(4));
				course.setCourseCatalogId(resultSet.getInt(5));
				course.setCourseFee(resultSet.getDouble(6));
				registeredCourses.add(course);
			}
		} catch (SQLException sqlErr) {
			logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));

		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				logger.error(
						String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));

			}
		}
		return registeredCourses;
	}
}