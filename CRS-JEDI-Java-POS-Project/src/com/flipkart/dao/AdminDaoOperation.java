package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

/**
 * Dao Class Operations for Admin
 *
 */
public class AdminDaoOperation implements AdminDaoInterface {

	private static AdminDaoOperation instance = null;
	private PreparedStatement statement = null;

	private AdminDaoOperation() {
	}

	public static AdminDaoOperation getInstance() {
		if (instance == null) {
			instance = new AdminDaoOperation();
		}
		return instance;
	}

	Connection connection = DBUtils.getConnection();

	// TODO : Fix me
	@Override
	public void deleteCourse(String courseCode) {

		statement = null;
		try {
			String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, courseCode);
			int row = statement.executeUpdate();

			System.err.println(row + " entries deleted.");
			if (row == 0) {
				System.err.println(courseCode + " not in catalog!");
			}

			System.out.println("Message -  ");
			System.out.println("Course with courseCode: " + courseCode + " deleted.");

		} catch (Exception se) {
			System.err.println(se.getMessage());
		}

	}

	// TODO : Fix me
	@Override
	public void addCourse(Course course) {

		statement = null;
		try {

			String sql = SQLQueriesConstants.ADD_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());

			int row = statement.executeUpdate();

			System.out.println("Message -  ");
			System.out.println(row + " course added");
			if (row == 0) {
				System.err.println("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
			}

			System.out.println("Message -  ");
			System.out.println("Course with courseCode: " + course.getCourseCode() + " is added to catalog.");

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}

	}

	@Override
	public List<Student> viewPendingAdmissions() {

		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {

			String sql = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Student user = new Student();
				// TODO : Update after query rewriten
				// 1 user.userId
				// 2 user.name
				// 3 user.email
				// 4 user.password
				// 5 user.role
				// 6 user.gender
				// 7 user.address
				// 8 user.country
				// 9 user.doj
				// 10 student.branchName
				// 11 student.batch
				// 12 student.rollNumber
				user.setUserId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setRole(Role.stringToName(resultSet.getString(5)));
				user.setGender(Gender.stringToGender(resultSet.getString(6)));
				user.setAddress(resultSet.getString(7));
				user.setCountry(resultSet.getString(8));
				user.setDoj(DBUtils.parseDate(resultSet.getString(9)));
				user.setBranchName(resultSet.getString(10));
				user.setBatch(resultSet.getInt(11));
				user.setrollNumber(resultSet.getString(12));
				userList.add(user);

			}

			System.out.println("Message - ");
			System.out.println(userList.size() + " students have pending-approval.");

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}

		return userList;

	}

	@Override
	public void approveStudent(int studentId) {

		statement = null;
		try {
			String sql = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setInt(1, studentId);
			int row = statement.executeUpdate();

			System.out.println("Message -  ");
			System.out.println(row + " student approved.");
			if (row == 0) {
				System.err.println("Student with studentId: " + studentId + " not found.");
				return;
				// TODO : Added exception Student was not approved
			}

			System.out.println("Message -  ");
			System.out.println("Student with studentId: " + studentId + " approved by admin.");

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	@Override
	public void addUser(User user) {

		try {
			String sql = SQLQueriesConstants.ADD_USER_QUERY;
			statement = connection.prepareStatement(sql);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRole().toString());
			statement.setString(5, user.getGender().toString());
			statement.setString(6, user.getAddress());
			statement.setString(7, user.getCountry());
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				System.err.println("User with userId: " + user.getUserId() + " not added.");
				// TODO : Throw USER EMAIL DUPLICATE EXCEPTION
				return;
			}

			//

			// SELECT u.*,s.* from student JOIN user u ON(student.userId=user.userId) where
			// student.isApproved = 0
			// statement = connection.prepareStatement("select userId,doj from users where
			// email = ?");
			// statement.setString(1, x);

			user.setUserId(rs.getInt("user.userId"));
			user.setDoj(DBUtils.parseDate(rs.getString("user.doj")));

		} catch (Exception se) {
			System.err.println(se.getMessage());
		}
	}

	@Override
	public void addProfessor(Professor professor) {

		try {
			this.addUser(professor);
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}

		try {

			String sql = SQLQueriesConstants.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setInt(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();

			System.out.println("Message -  ");
			System.out.println(row + " professor added.");
			if (row == 0) {
				System.err.println("Professor with professorId: " + professor.getUserId() + " not added.");
				return;
				// TODO : Added Some error in Professor creation
			}

			System.out.println("Message -  ");
			System.out.println("Professor with professorId: " + professor.getUserId() + " added.");

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}

	}

	// TODO : Fix me
	@Override
	public void assignCourse(String courseCode, String professorId) {

		statement = null;
		try {
			String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1, professorId);
			statement.setString(2, courseCode);
			int row = statement.executeUpdate();

			System.out.println("Message -  ");
			System.out.println(row + " course assigned.");
			if (row == 0) {
				System.err.println(courseCode + " not found");
			}

			System.out.println("Message -  ");
			System.out.println("Course with courseCode: " + courseCode + " is assigned to professor with professorId: "
					+ professorId + ".");

		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}

	}

	// TODO : Fix me
	public List<Course> viewCourses(int catalogId) {

		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {

			String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Course course = new Course();
				course.setCourseCode(resultSet.getString(1));
				course.setCourseName(resultSet.getString(2));
				course.setProfessorId(resultSet.getString(3));
				courseList.add(course);

			}

			System.out.println("Message -  ");
			System.out.println(courseList.size() + " courses in catalogId: " + catalogId + ".");

		} catch (SQLException se) {

			System.err.println(se.getMessage());

		}

		return courseList;

	}

	@Override
	public List<Professor> viewProfessors() {

		statement = null;
		List<Professor> professorList = new ArrayList<>();
		try {

			String sql = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			//

			// select user.userId, user.name, user.gender, professor.department,
			// professor.designation, user.address, user.country from
			// professor inner join User on user.userId = professor.id
			while (resultSet.next()) {

				Professor professor = new Professor();
				professor.setUserId(resultSet.getInt(1));
				professor.setName(resultSet.getString(2));
				professor.setGender(Gender.stringToGender(resultSet.getString(3)));
				professor.setDepartment(resultSet.getString(4));
				professor.setDesignation(resultSet.getString(5));
				professor.setAddress(resultSet.getString(6));
				professor.setCountry(resultSet.getString(7));
				professor.setDoj(DBUtils.parseDate(resultSet.getString(8)));
				professor.setRole(Role.PROFESSOR);
				professor.setPassword("*********");
				professorList.add(professor);
			}

			System.out.println("Message -  ");
			System.out.println(professorList.size() + " professors in the institute.");

		} catch (SQLException se) {

			System.err.println(se.getMessage());

		}
		return professorList;
	}
}