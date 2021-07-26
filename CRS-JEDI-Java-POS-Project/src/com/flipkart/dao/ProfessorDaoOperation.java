package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
// import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.User;
import com.flipkart.constant.Grade;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

/**
 * 
 * Class to implement Professor Dao Operations
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static ProfessorDaoOperation instance = null;
	UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();

	private ProfessorDaoOperation() {

	}

	public static ProfessorDaoOperation getInstance() {
		if (instance == null) {
			instance = new ProfessorDaoOperation();
		}
		return instance;
	}

	@Override
	public boolean createDBRecordAndUpdateObject(Professor professor) {
		if (!userDaoInterface.createDBRecordAndUpdateObject(professor))
			return false;
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_PROFESSOR_QUERY;
		try (PreparedStatement preparedStatementprofessor = connection.prepareStatement(queryToExecute);) {
			// userId, department, designation
			preparedStatementprofessor.setInt(1, professor.getUserId());
			preparedStatementprofessor.setString(2, professor.getDepartment());
			preparedStatementprofessor.setString(3, professor.getDesignation());
			int rowsAffected = preparedStatementprofessor.executeUpdate();
			if (rowsAffected == 0) {
				userDaoInterface.deleteUserObjectFromUserId(professor.getUserId());
				return false;
				// TODO : Add exception professor Record Not created
			}
			return true;

		} catch (SQLException sqlErr) {
			userDaoInterface.deleteUserObjectFromUserId(professor.getUserId());
			System.err.printf("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Professor getProfessorFromUserIdImpl(int userId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_PROFESSOR_FROM_USERID;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
				// @yaduraj
				// TODO : Add exception User Record not found
			}
			// userId, department, designation
			Professor professor = new Professor();
			professor.setUserId(resultSet.getInt(1));
			professor.setDepartment(resultSet.getString(2));
			professor.setDesignation(resultSet.getString(3));
			return professor;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return null;

	}

	@Override
	public Professor getProfessorFromUserId(int userId) {
		User user = userDaoInterface.getUserFromUserId(userId);
		if (user == null)
			return null;
		return new Professor(user);

	}

	@Override
	public Professor getProfessorFromEmail(String email) {
		User user = userDaoInterface.getUserFromEmail(email);
		if (user == null)
			return null;
		return new Professor(user);
	}

	@Override
	public List<Course> getCoursesByProfessorUserId(int professorUserId) {
		Connection connection = DBUtils.getConnection();
		List<Course> courseList = new ArrayList<>();
		String queryToExecute = SQLQueriesConstants.GET_COURSES_BY_PROFESSOR_USER_ID;
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {
			statement.setInt(1, professorUserId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Course course = new Course();
				// courseId, courseCode, courseName, professorUserId, courseCatalogId
				course.setCourseId(resultSet.getInt(1));
				course.setCourseCode(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				course.setProfessorUserId(resultSet.getInt(4));
				course.setCourseCatalogId(resultSet.getInt(5));
				courseList.add(course);
			}
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return courseList;
	}

	@Override
	public String getProfessorNameByUserId(int userId) {
		User user = userDaoInterface.getUserFromUserId(userId);
		if (user == null)
			return null;
		return user.getName();
	}

	@Override
	public List<RegisteredCourse> getEnrolledStudents(int professorUserId) {
		Connection connection = DBUtils.getConnection();
		List<RegisteredCourse> enrolledStudents = new ArrayList<>();
		String queryToExecute = SQLQueriesConstants.GET_ALL_REGISTERED_COURSE_IDS_FROM_PROFESSOR_ID;
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute);) {
			statement.setInt(1, professorUserId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int registeredCourseId = resultSet.getInt(1);
				int studentUserId = resultSet.getInt(2);
				int courseId = resultSet.getInt(3);
				Grade grade = Grade.valueOf(resultSet.getString(4));
				RegisteredCourse registeredCourse = new RegisteredCourse();
				registeredCourse.setRegisteredCourseId(registeredCourseId);
				registeredCourse.setStudentUserId(studentUserId);
				registeredCourse.setCourseId(courseId);
				registeredCourse.setGrade(grade);
				enrolledStudents.add(registeredCourse);
			}
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return enrolledStudents;
	}

	@Override
	public boolean addGradeToStudent(int studentUserId, int courseId, Grade grade) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_GRADE_FROM_STUDENT_ID_AND_COURSE_ID;
		try (PreparedStatement preparedStatementprofessor = connection.prepareStatement(queryToExecute);) {
			preparedStatementprofessor.setString(1, grade.toString());
			preparedStatementprofessor.setInt(2, studentUserId);
			preparedStatementprofessor.setInt(3, courseId);
			int rowsAffected = preparedStatementprofessor.executeUpdate();
			if (rowsAffected == 0) {
				return false;
			}
			return true;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}
}
