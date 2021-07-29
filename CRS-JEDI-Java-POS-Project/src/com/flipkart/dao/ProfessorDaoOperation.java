package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

/**
 *
 * Class to implement Professor Dao Operations
 *
 */
public class ProfessorDaoOperation implements ProfessorDaoInterface {

	private static volatile ProfessorDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);

	private ProfessorDaoOperation() {

	}

	public static ProfessorDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (ProfessorDaoOperation.class) {
				instance = new ProfessorDaoOperation();
			}
		}
		return instance;
	}

	@Override
	public List<Course> getCoursesByProfessor(String profId) {
		Connection connection = DBUtils.getConnection();
		List<Course> courseList = new ArrayList<Course>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_COURSES);

			statement.setString(1, profId);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				courseList.add(new Course(results.getString("courseCode"), results.getString("courseName"),
						results.getString("professorId"), results.getInt("seats")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return courseList;

	}

	@Override
	public List<EnrolledStudent> getEnrolledStudents(String profId) {
		Connection connection = DBUtils.getConnection();
		List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
			statement.setString(1, profId);

			ResultSet results = statement.executeQuery();
			while (results.next()) {
				// public EnrolledStudent(String courseCode, String courseName, int studentId)
				enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),
						results.getString("courseName"), results.getInt("studentId")));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return enrolledStudents;
	}

	public Boolean addGrade(int studentId, String courseCode, String grade) {
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.ADD_GRADE);

			statement.setString(1, grade);
			statement.setString(2, courseCode);
			statement.setInt(3, studentId);

			int row = statement.executeUpdate();

			if (row == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getProfessorById(String profId) {
		String prof_Name = null;
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_PROF_NAME);

			statement.setString(1, profId);
			ResultSet rs = statement.executeQuery();
			rs.next();

			prof_Name = rs.getString(1);

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return prof_Name;
	}
}
