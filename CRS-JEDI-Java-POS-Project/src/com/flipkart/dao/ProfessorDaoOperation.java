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
import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

/**
 * 
 * Class to implement Professor Dao Operations
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static ProfessorDaoOperation instance = null;

	private ProfessorDaoOperation() {

	}

	public static boolean createDBRecordAndUpdateObject(Professor professor) {
		if (!UserDaoOperation.createDBRecordAndUpdateObject(professor))
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
				UserDaoOperation.deleteUserObjectFromUserId(professor.getUserId());
				return false;
				// TODO : Add exception professor Record Not created
			}
			return true;

		} catch (SQLException sqlErr) {
			UserDaoOperation.deleteUserObjectFromUserId(professor.getUserId());
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

	public static Professor getProfessorFromUserIdImpl(int userId) {
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

	public static Professor getProfessorFromUserId(int userId) {
		User user = UserDaoOperation.getUserFromUserId(userId);
		if (user == null)
			return null;
		return new Professor(user);

	}

	public static Professor getProfessorFromEmail(String email) {
		User user = UserDaoOperation.getUserFromEmail(email);
		if (user == null)
			return null;
		return new Professor(user);
	}

	public static ProfessorDaoOperation getInstance() {
		if (instance == null) {
			instance = new ProfessorDaoOperation();
		}
		return instance;
	}

	// @Override
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

	// @Override
	// public List<RegisteredCourse> getEnrolledStudents(int profId) {
	// Connection connection = DBUtils.getConnection();
	// List<RegisteredCourse> enrolledStudents = new ArrayList<>();
	// String QueryToExecute = SQLQueriesConstants.GET_ENROLLED_STUDENTS;
	// try {
	// PreparedStatement statement = connection.prepareStatement(QueryToExecute);
	// statement.setInt(1, profId);

	// ResultSet results = statement.executeQuery();
	// while (results.next()) {
	// // public EnrolledStudent(String courseCode, String courseName, int
	// studentId)
	// enrolledStudents.add(new RegisteredCourse(results.getString("courseCode"),
	// results.getString("courseName"), results.getString("studentId")));
	// }
	// } catch (SQLException sqlErr) {
	// System.err.printf("Error in Executing Query %s\n%s\n", QueryToExecute,
	// sqlErr.getMessage());
	// sqlErr.printStackTrace();
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException closeErr) {
	// System.err.printf("Error in Closing Connection %s\n%s\n", QueryToExecute,
	// closeErr.getMessage());
	// closeErr.printStackTrace();
	// }
	// }
	// return enrolledStudents;
	// }

	// public Boolean addGrade(int studentId, String courseCode, String grade) {
	// Connection connection = DBUtils.getConnection();
	// try {
	// PreparedStatement statement =
	// connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);

	// statement.setString(1, grade);
	// statement.setString(2, courseCode);
	// statement.setInt(3, studentId);

	// int row = statement.executeUpdate();

	// if (row == 1)
	// return true;
	// else
	// return false;
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// } finally {
	// try {
	// connection.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// return false;
	// }

	@Override
	public String getProfessorNameByUserId(int userId) {
		User user = UserDaoOperation.getUserFromUserId(userId);
		if (user == null)
			return null;
		return user.getName();
	}
}
