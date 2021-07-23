package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import com.mysql.cj.xdevapi.Statement;

/**
 * Dao Class Operations for Admin
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {

	private static AdminDaoOperation instance = null;

	private AdminDaoOperation() {

	}

	public static AdminDaoOperation getInstance() {
		if (instance == null) {
			instance = new AdminDaoOperation();
		}
		return instance;
	}

	public static boolean createDBRecordAndUpdateObject(Admin admin) {
		if (!UserDaoOperation.createDBRecordAndUpdateObject(admin))
			return false;
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_ADMIN_QUERY;
		try {
			PreparedStatement preparedStatementadmin = connection.prepareStatement(queryToExecute);
			preparedStatementadmin.setInt(1, admin.getUserId());
			int rowsAffected = preparedStatementadmin.executeUpdate();
			if (rowsAffected == 0) {
				UserDaoOperation.deleteUserObjectFromUserId(admin.getUserId());
				return false;
				// TODO : Add exception admin Record Not created
			}
			return true;
		} catch (SQLException sqlErr) {
			UserDaoOperation.deleteUserObjectFromUserId(admin.getUserId());
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	public static Admin getAdminFromUserId(int userId) {
		User user = UserDaoOperation.getUserFromUserId(userId);
		if (user == null)
			return null;
		return new Admin(user);
	}

	public static Admin getAdminFromEmail(String email) {
		User user = UserDaoOperation.getUserFromEmail(email);
		if (user == null)
			return null;
		return new Admin(user);
	}

	@Override
	public List<Student> viewPendingAdmissions() {

		List<Student> userList = new ArrayList<Student>();
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
		try {

			PreparedStatement statement = connection.prepareStatement(queryToExecute);
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
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return userList;

	}

	@Override
	public void approveStudent(int userId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
		try {
			PreparedStatement statement = connection.prepareStatement(queryToExecute);
			statement.setInt(1, userId);
			int row = statement.executeUpdate();

			// System.out.println("Message - ");
			// System.out.println(row + " student approved.");
			if (row == 0) {
				// System.err.println("Student with userId: " + userId + " not found.");
				return;
				// TODO : Added exception Student was not approved
			}

			// System.out.println("Message - ");
			// System.out.println("Student with userId: " + userId + " approved by admin.");

		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
	}

	@Override
	public void addProfessor(Professor professor) {
		ProfessorDaoOperation.createDBRecordAndUpdateObject(professor);
	}

	@Override
	public List<Professor> viewProfessors() {

		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
		List<Professor> professorList = new ArrayList<>();
		try {

			PreparedStatement statement = connection.prepareStatement(queryToExecute);
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
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return professorList;
	}

	// // TODO : Fix me
	// @Override
	// public void deleteCourse(String courseCode) {

	// Connection connection = DBUtils.getConnection();
	// String
	// try {
	// String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
	// PreparedStatement statement = connection.prepareStatement(sql);

	// statement.setString(1, courseCode);
	// int row = statement.executeUpdate();

	// System.err.println(row + " entries deleted.");
	// if (row == 0) {
	// System.err.println(courseCode + " not in catalog!");
	// }

	// System.out.println("Message - ");
	// System.out.println("Course with courseCode: " + courseCode + " deleted.");

	// } catch (Exception se) {
	// System.err.println(se.getMessage());
	// }

	// }

	// // TODO : Fix me
	// @Override
	// public void addCourse(Course course) {

	// statement = null;
	// try {

	// String sql = SQLQueriesConstants.ADD_COURSE_QUERY;
	// statement = connection.prepareStatement(sql);
	// statement.setString(1, course.getCourseCode());
	// statement.setString(2, course.getCourseName());
	// int row = statement.executeUpdate();

	// System.out.println("Message - ");
	// System.out.println(row + " course added");
	// if (row == 0) {
	// System.err.println("Course with courseCode: " + course.getCourseCode() + "not
	// added to catalog.");
	// }

	// System.out.println("Message - ");
	// System.out.println("Course with courseCode: " + course.getCourseCode() + " is
	// added to catalog.");

	// } catch (SQLException se) {
	// System.err.println(se.getMessage());
	// }

	// }

	// // TODO:Fix me

	// @Override
	// public void assignCourse(String courseCode, String professorId) {

	// statement = null;
	// try {
	// String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
	// statement = connection.prepareStatement(sql);

	// statement.setString(1, professorId);
	// statement.setString(2, courseCode);
	// int row = statement.executeUpdate();

	// System.out.println("Message - ");
	// System.out.println(row + " course assigned.");
	// if (row == 0) {
	// System.err.println(courseCode + " not found");
	// }

	// System.out.println("Message - ");
	// System.out.println("Course with courseCode: " + courseCode + " is assigned to
	// professor with professorId: "
	// + professorId + ".");

	// } catch (SQLException se) {
	// System.err.println(se.getMessage());
	// }

	// }

	// // TODO : Fix me
	// public List<Course> viewCourses(int catalogId) {

	// statement = null;
	// List<Course> courseList = new ArrayList<>();
	// try {

	// String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;
	// statement = connection.prepareStatement(sql);
	// statement.setInt(1, catalogId);
	// ResultSet resultSet = statement.executeQuery();

	// while (resultSet.next()) {

	// Course course = new Course();
	// course.setCourseCode(resultSet.getString(1));
	// course.setCourseName(resultSet.getString(2));
	// course.setProfessorId(resultSet.getString(3));
	// courseList.add(course);

	// }

	// System.out.println("Message - ");
	// System.out.println(courseList.size() + " courses in catalogId: " + catalogId
	// + ".");

	// } catch (SQLException se) {

	// System.err.println(se.getMessage());

	// }

	// return courseList;

	// }

}