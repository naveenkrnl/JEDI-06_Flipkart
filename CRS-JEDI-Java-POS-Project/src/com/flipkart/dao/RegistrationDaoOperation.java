package com.flipkart.dao;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.List;

// import com.flipkart.bean.Course;
// import com.flipkart.bean.GradeCard;
// import com.flipkart.constant.Grade;
// import com.flipkart.constant.SQLQueriesConstants;
// import com.flipkart.utils.DBUtils;

public class RegistrationDaoOperation implements RegistrationDaoInterface {

	private static RegistrationDaoOperation instance = null;

	private RegistrationDaoOperation() {
	}

	public static RegistrationDaoOperation getInstance() {
		if (instance == null) {
			synchronized (RegistrationDaoOperation.class) {
				instance = new RegistrationDaoOperation();
			}
		}
		return instance;
	}

	// @Override
	// public boolean addCourse(String courseCode, int studentId) throws
	// SQLException {

	// Connection conn = DBUtils.getConnection();

	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
	// stmt.setInt(1, studentId);
	// stmt.setString(2, courseCode);

	// stmt.executeUpdate();

	// stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
	// stmt.setString(1, courseCode);
	// stmt.executeUpdate();
	// return true;
	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// } finally {
	// stmt.close();
	// conn.close();
	// }
	// return false;

	// }

	// @Override
	// public int numOfRegisteredCourses(int studentId) throws SQLException {

	// Connection conn = DBUtils.getConnection();

	// int count = 0;
	// try {

	// stmt =
	// conn.prepareStatement(SQLQueriesConstants.NUMBER_OF_REGISTERED_COURSES);
	// stmt.setInt(1, studentId);
	// ResultSet rs = stmt.executeQuery();
	// while (rs.next()) {
	// count++;
	// }
	// return count;

	// } catch (SQLException se) {

	// System.err.println(se.getMessage());

	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return count;
	// }

	// @Override
	// public boolean seatAvailable(String courseCode) throws SQLException {

	// Connection conn = DBUtils.getConnection();
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.GET_SEATS);
	// stmt.setString(1, courseCode);
	// ResultSet rs = stmt.executeQuery();
	// while (rs.next()) {
	// return (rs.getInt("seats") > 0);
	// }

	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return true;

	// }

	// @Override
	// public boolean isRegistered(String courseCode, int studentId) throws
	// SQLException {

	// Connection conn = DBUtils.getConnection();

	// boolean check = false;
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.IS_REGISTERED);
	// stmt.setString(1, courseCode);
	// stmt.setInt(2, studentId);
	// ResultSet rs = stmt.executeQuery();

	// while (rs.next()) {
	// check = true;
	// }
	// } catch (Exception e) {
	// System.err.println(e.getClass());
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());
	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return check;

	// }

	// @Override
	// public boolean dropCourse(String courseCode, int studentId) throws
	// SQLException {

	// Connection conn = DBUtils.getConnection();

	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.DROP_COURSE_QUERY);
	// stmt.setString(1, courseCode);
	// stmt.setInt(2, studentId);
	// stmt.execute();

	// stmt = conn.prepareStatement(SQLQueriesConstants.INCREMENT_SEAT_QUERY);
	// stmt.setString(1, courseCode);
	// stmt.execute();

	// stmt.close();

	// return true;
	// } catch (Exception e) {
	// System.out.println("Message - ");
	// System.out.println("Exception found" + e.getMessage());
	// } finally {

	// stmt.close();
	// conn.close();
	// }

	// return false;

	// }

	// @Override
	// public double calculateFee(int studentId) throws SQLException {
	// Connection conn = DBUtils.getConnection();
	// double fee = 0;
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.CALCULATE_FEES);
	// stmt.setInt(1, studentId);
	// ResultSet rs = stmt.executeQuery();
	// rs.next();
	// fee = rs.getDouble(1);
	// } catch (SQLException e) {
	// System.err.println(e.getErrorCode());
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());
	// } catch (Exception e) {
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());
	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return fee;
	// }

	// @Override
	// public List<GradeCard> viewGradeCard(int studentId) throws SQLException {

	// Connection conn = DBUtils.getConnection();
	// List<GradeCard> grade_List = new ArrayList<>();
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_GRADE);
	// stmt.setInt(1, studentId);
	// ResultSet rs = stmt.executeQuery();

	// while (rs.next()) {
	// String courseCode = rs.getString("courseCode");
	// String courseName = rs.getString("courseName");
	// String grade = rs.getString("grade");
	// int semester = rs.getInt("semester"); // Added semester
	// GradeCard obj = new GradeCard(courseCode, courseName,
	// Grade.stringToGrade(grade), semester);
	// grade_List.add(obj);
	// }
	// } catch (SQLException e) {
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());
	// } finally {
	// stmt.close();
	// conn.close();

	// }

	// return grade_List;
	// }

	// @Override
	// public List<Course> viewCourses(int studentId) throws SQLException {

	// List<Course> availableCourseList = new ArrayList<>();
	// Connection conn = DBUtils.getConnection();

	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_AVAILABLE_COURSES);
	// stmt.setInt(1, studentId);
	// stmt.setBoolean(2, true);
	// ResultSet rs = stmt.executeQuery();

	// while (rs.next()) {
	// // availableCourseList.add(new Course(rs.getString("courseCode"),
	// // rs.getString("courseName"),
	// // rs.getString("professorId"), rs.getInt("seats")));

	// }

	// } catch (SQLException e) {
	// System.err.println(e.getMessage());
	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return availableCourseList;

	// }

	// @Override
	// public List<Course> viewRegisteredCourses(int studentId) throws SQLException
	// {

	// Connection conn = DBUtils.getConnection();
	// List<Course> registeredCourseList = new ArrayList<>();
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);
	// stmt.setInt(1, studentId);

	// ResultSet rs = stmt.executeQuery();

	// while (rs.next()) {
	// // registeredCourseList.add(new Course(rs.getString("courseCode"),
	// // rs.getString("courseName"),
	// // rs.getString("professorId"), rs.getInt("seats")));

	// }
	// } catch (SQLException e) {
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());

	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return registeredCourseList;
	// }

	// @Override
	// public boolean getRegistrationStatus(int studentId) throws SQLException {
	// Connection conn = DBUtils.getConnection();
	// boolean status = false;
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.GET_REGISTRATION_STATUS);
	// stmt.setInt(1, studentId);
	// ResultSet rs = stmt.executeQuery();
	// rs.next();
	// status = rs.getBoolean(1);

	// } catch (SQLException e) {
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());

	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// return status;
	// }

	// @Override
	// public void setRegistrationStatus(int studentId) throws SQLException {
	// Connection conn = DBUtils.getConnection();
	// try {
	// stmt = conn.prepareStatement(SQLQueriesConstants.SET_REGISTRATION_STATUS);
	// stmt.setInt(1, studentId);
	// stmt.executeUpdate();

	// } catch (SQLException e) {
	// System.out.println("Message - ");
	// System.out.println(e.getMessage());

	// } finally {
	// stmt.close();
	// conn.close();
	// }

	// }

}