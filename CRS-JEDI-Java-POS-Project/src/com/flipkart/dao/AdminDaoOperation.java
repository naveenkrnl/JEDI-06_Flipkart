package com.flipkart.dao;

import com.flipkart.bean.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao Class Operations for Admin
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {

	private static AdminDaoOperation instance = null;
	final UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();
	static Logger logger = Logger.getLogger("");

	private AdminDaoOperation() {

	}

	public static AdminDaoOperation getInstance() {
		if (instance == null) {
			instance = new AdminDaoOperation();
		}
		return instance;
	}

	@Override
	public boolean createDBRecordAndUpdateObject(Admin admin) {
		if (!userDaoInterface.createDBRecordAndUpdateObject(admin))
			return false;
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_ADMIN_QUERY;
		try (PreparedStatement preparedStatementAdmin = connection.prepareStatement(queryToExecute)) {

			preparedStatementAdmin.setInt(1, admin.getUserId());
			int rowsAffected = preparedStatementAdmin.executeUpdate();
			if (rowsAffected == 0) {
				userDaoInterface.deleteUserObjectFromUserId(admin.getUserId());
				return false;
			}
			return true;
		} catch (SQLException sqlErr) {
			userDaoInterface.deleteUserObjectFromUserId(admin.getUserId());
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
	public Admin getAdminFromUserId(int userId) {
		User user = userDaoInterface.getUserFromUserId(userId);
		if (user == null)
			return null;
		return new Admin(user);
	}

	@Override
	public Admin getAdminFromEmail(String email) {
		User user = userDaoInterface.getUserFromEmail(email);
		if (user == null)
			return null;
		return new Admin(user);
	}

	@Override
	public List<Student> viewUnapprovedStudents() {

		List<Student> userList = new ArrayList<>();
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Student student = new Student();
				student.setUserId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setEmail(resultSet.getString(3));
				student.setPassword(resultSet.getString(4));
				student.setRole(Role.valueOf(resultSet.getString(5)));
				student.setGender(Gender.valueOf(resultSet.getString(6)));
				student.setAddress(resultSet.getString(7));
				student.setCountry(resultSet.getString(8));
				student.setDoj(DBUtils.parseDate(resultSet.getString(9)));
				student.setBranchName(resultSet.getString(10));
				student.setBatch(resultSet.getInt(11));
				student.setrollNumber(resultSet.getString(12));
				userList.add(student);
			}

			// System.out.println("Message - ");
			// System.out.println(userList.size() + " students have pending-approval.");

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
		return userList;

	}

	@Override
	public boolean approveStudent(int userId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {

			statement.setInt(1, userId);
			int row = statement.executeUpdate();
			return row > 0;

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
	public void addProfessor(Professor professor) {
		ProfessorDaoOperation.getInstance().createDBRecordAndUpdateObject(professor);
	}

	@Override
	public List<Professor> viewProfessors() {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
		List<Professor> professorList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {
			ResultSet resultSet = statement.executeQuery();

			// user.name, user.gender, user.address, user.country, user.userId, user.role,
			// user.password, user.email, user.doj, professor.department,
			// professor.designation
			while (resultSet.next()) {

				Professor professor = new Professor();
				professor.setName(resultSet.getString(1));
				professor.setGender(Gender.valueOf(resultSet.getString(2)));
				professor.setAddress(resultSet.getString(3));
				professor.setCountry(resultSet.getString(4));
				professor.setUserId(resultSet.getInt(5));
				professor.setRole(Role.valueOf(resultSet.getString(6)));
				professor.setPassword(resultSet.getString(7));
				professor.setEmail(resultSet.getString(8));
				professor.setDoj(DBUtils.parseDate(resultSet.getString(9)));
				professor.setDesignation(resultSet.getString(10));
				professor.setDepartment(resultSet.getString(11));
				professorList.add(professor);
			}

			// System.out.println("Message - ");
			// System.out.println(professorList.size() + " professors in the institute.");

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
		return professorList;
	}

	@Override
	public boolean createCourseDBRecordAndUpdateObject(Course course) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute;
		if (course.getProfessorUserId() != -1) {
			queryToExecute = SQLQueriesConstants.ADD_COURSE_QUERY_WITH_PROFESSOR_USER_ID;
		} else {
			queryToExecute = SQLQueriesConstants.ADD_COURSE_QUERY;
		}

		try (PreparedStatement preparedStatementCourse = connection.prepareStatement(queryToExecute)) {
			// courseCode, courseName, courseCatalogId, professorUserId

			preparedStatementCourse.setString(1, course.getCourseCode());
			preparedStatementCourse.setString(2, course.getCourseName());
			preparedStatementCourse.setInt(3, course.getCourseCatalogId());
			preparedStatementCourse.setDouble(4, course.getCourseFee());
			if (course.getProfessorUserId() != -1)
				preparedStatementCourse.setInt(5, course.getProfessorUserId());
			int rowsAffected = preparedStatementCourse.executeUpdate();
			if (rowsAffected == 0) {
				return false;
			}
			Course courseFromDB = getCourseFromCourseCodeAndCatalogId(course.getCourseCode(),
					course.getCourseCatalogId());
			assert courseFromDB != null;
			course.setCourseId(courseFromDB.getCourseId());
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
	public Course getCourseFromCourseCodeAndCatalogId(String courseCode, int catalogId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_COURSE_FROM_COURSE_CODE_AND_CATALOG_ID;
		try (PreparedStatement preparedStatementCourse = connection.prepareStatement(queryToExecute)) {

			preparedStatementCourse.setString(1, courseCode);
			preparedStatementCourse.setInt(2, catalogId);
			ResultSet resultSet = preparedStatementCourse.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			// courseId, courseCode, courseName, professorUserId, courseCatalogId, courseFee

			Course course = new Course();
			course.setCourseId(resultSet.getInt(1));
			course.setCourseCode(resultSet.getString(2));
			course.setCourseName(resultSet.getString(3));
			course.setProfessorUserId(resultSet.getInt(4));
			course.setCourseCatalogId(resultSet.getInt(5));
			course.setCourseFee(resultSet.getDouble(6));
			return course;
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
	public Course getCourseFromCourseId(int courseId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_COURSE_FROM_COURSE_ID;
		try (PreparedStatement preparedStatementCourse = connection.prepareStatement(queryToExecute)) {

			preparedStatementCourse.setInt(1, courseId);
			ResultSet resultSet = preparedStatementCourse.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			// courseId, courseCode, courseName, professorUserId, courseCatalogId

			Course course = new Course();
			course.setCourseId(resultSet.getInt(1));
			course.setCourseCode(resultSet.getString(2));
			course.setCourseName(resultSet.getString(3));
			course.setProfessorUserId(resultSet.getInt(4));
			course.setCourseCatalogId(resultSet.getInt(5));
			course.setCourseFee(resultSet.getDouble(6));
			return course;
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
	public boolean addCourse(Course course) {
		return createCourseDBRecordAndUpdateObject(course);
	}

	// @Override
	@Override
	public boolean assignCourseToProfessor(int courseId, int professorUserId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ASSIGN_COURSE_TO_PROFESSOR_FROM_COURSE_ID;
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {

			statement.setInt(1, professorUserId);
			statement.setInt(2, courseId);
			int row = statement.executeUpdate();

			// System.out.println("Message - ");
			// System.out.println(row + " course assigned.");
			if (row == 0) {
				// System.err.println(courseId + " not found");
				return false;
			}

			// System.out.println("Message - ");
			// System.out.println("Course with courseCode: " + courseCode
			// + " is assigned to professor with professorUserId: " + professorUserId +
			// ".");
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

	// @Override
	@Override
	public boolean deleteCourse(int courseId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.DELETE_COURSE_FROM_FROM_COURSE_ID;
		try (PreparedStatement statement1 = connection
				.prepareStatement(SQLQueriesConstants.DELETE_REGISTERED_COURSE_FROM_COURSE_ID);
				PreparedStatement statement2 = connection.prepareStatement(queryToExecute)) {

			statement1.setInt(1, courseId);
			statement1.executeUpdate();

			statement2.setInt(1, courseId);
			int rowsAffected = statement2.executeUpdate();
			return rowsAffected != 0;
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
	public List<Course> viewCourses(int courseCatalogId) {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_COURSE_LIST_FROM_COURSE_CATALOG_ID;
		List<Course> courseList = new ArrayList<>();
		try (PreparedStatement statement = connection.prepareStatement(queryToExecute)) {

			// String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;

			statement.setInt(1, courseCatalogId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Course course = new Course();
				// courseId, courseCode, courseName, professorUserId, courseCatalogId
				course.setCourseId(resultSet.getInt(1));
				course.setCourseCode(resultSet.getString(2));
				course.setCourseName(resultSet.getString(3));
				course.setProfessorUserId(resultSet.getInt(4));
				course.setCourseCatalogId(resultSet.getInt(5));
				course.setCourseFee(resultSet.getDouble(6));
				courseList.add(course);
			}

			// System.out.println("Message - ");
			// System.out.println(courseList.size() + " courses in catalogId: " + catalogId
			// + ".");

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
		return courseList;
	}

}