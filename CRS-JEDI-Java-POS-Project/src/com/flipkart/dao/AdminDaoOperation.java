package com.flipkart.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.flipkart.exception.*;
import org.apache.log4j.Logger;

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
public class AdminDaoOperation implements AdminDaoInterface{

	private static volatile AdminDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	private PreparedStatement statement = null;
	
	/**
	 * Default Constructor
	 */
	private AdminDaoOperation(){}
	
	/**
	 * Method to make AdminDaoOperation Singleton
	 * @return
	 */
	public static AdminDaoOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminDaoOperation.class){
				instance = new AdminDaoOperation();
			}
		}
		return instance;
	}
	
	Connection connection = DBUtils.getConnection();

	/**
	 * Method to add student to database
	 *
	 * @param admin: User object containing all the fields for admin
	 * @return Admin id if admin account is created else 0
	 * @throws AdminAccountNotCreatedException
	 */
	@Override
	public int addAdmin(User admin) throws AdminAccountNotCreatedException {
		Connection connection = DBUtils.getConnection();
		int adminId = 0;
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, admin.getUserId());
			preparedStatement.setString(2, admin.getName());
			preparedStatement.setString(3, admin.getPassword());
			preparedStatement.setString(4, admin.getRole().toString());
			preparedStatement.setString(5, admin.getGender().toString());
			preparedStatement.setString(6, admin.getAddress());
			preparedStatement.setString(7, admin.getCountry());
			int rowsAffected = preparedStatement.executeUpdate();
			ResultSet results = preparedStatement.getGeneratedKeys();
			if (results.next())
				adminId = results.getInt(1);
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
			throw new AdminAccountNotCreatedException();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage() + "SQL error");
				e.printStackTrace();
			}
		}
		return adminId;
	}

	/**
	 * Delete Course using SQL commands
	 * @param courseCode
	 * @throws CourseNotFoundException
	 * @throws CourseNotDeletedException 
	 */
	@Override
	public void deleteCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,courseCode);
			int row = statement.executeUpdate();
			
			logger.info(row + " entries deleted.");
			if(row == 0) {
				logger.error(courseCode + " not in catalog!");
				throw new CourseNotFoundException(courseCode);
			}

			logger.info("Course with courseCode: " + courseCode + " deleted.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new CourseNotDeletedException(courseCode);
		}
		
	}

	/**
	 * Add Course using SQL commands
	 * @param course
	 * @throws CourseFoundException
	 */
	@Override
	public void addCourse(Course course) throws CourseFoundException{
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());
			
			statement.setInt(3, 1);
			int row = statement.executeUpdate();
			
			logger.info(row + " course added");
			if(row == 0) {
				logger.error("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
				throw new CourseFoundException(course.getCourseCode());
			}
			
			logger.info("Course with courseCode: " + course.getCourseCode() + " is added to catalog."); 
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new CourseFoundException(course.getCourseCode());
			
		}
		
	}

	/**
	 * Fetch Students yet to approved using SQL commands
	 * @return List of Students yet to approved
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		
		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			String sql = SQLQueriesConstants.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setUserId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(Role.stringToName(resultSet.getString(4)));
				user.setGender(Gender.stringToGender( resultSet.getString(5)));
				user.setAddress(resultSet.getString(6));
				user.setCountry(resultSet.getString(7));
				user.setStudentId(resultSet.getInt(8));
				userList.add(user);
				
			}
			
			logger.info(userList.size() + " students have pending-approval.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		
		return userList;
		
	}

	/**
	 * Approve Student using SQL commands
	 * @param studentId
	 * @throws StudentNotFoundForApprovalException
	 */
	@Override
	public void approveStudent(int studentId) throws StudentNotFoundForApprovalException {
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1,studentId);
			int row = statement.executeUpdate();
			
			logger.info(row + " student approved.");
			if(row == 0) {
				//logger.error("Student with studentId: " + studentId + " not found.");
				throw new StudentNotFoundForApprovalException(studentId);
			}
			
			//logger.info("Student with studentId: " + studentId + " approved by admin.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		
	}

	/**
	 * Method to add user using SQL commands
	 * @param user
	 * @throws UserNotAddedException
	 * @throws UserIdAlreadyInUseException 
	 */
	@Override
	public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException{
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_USER_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRole().toString());
			statement.setString(5, user.getGender().toString());
			statement.setString(6, user.getAddress());
			statement.setString(7, user.getCountry());
			int row = statement.executeUpdate();
			
			logger.info(row + " user added.");
			if(row == 0) {
				logger.error("User with userId: " + user.getUserId() + " not added.");
				throw new UserNotAddedException(user.getUserId());
			}

			logger.info("User with userId: " + user.getUserId() + " added."); 
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new UserIdAlreadyInUseException(user.getUserId());
			
		}
		
	}

	/**
	 * Add professor using SQL commands
	 * @param professor
	 * @throws UserIdAlreadyInUseException 
	 * @throws ProfessorNotAddedException 
	 */
	@Override
	public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException {
		
		try {
			
			this.addUser(professor);
			
		}catch (UserNotAddedException e) {
			
			logger.error(e.getMessage());
			throw new ProfessorNotAddedException(professor.getUserId());
			
		}catch (UserIdAlreadyInUseException e) {
			
			logger.error(e.getMessage());
			throw e;
			
		}
		
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstants.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();

			logger.info(row + " professor added.");
			if(row == 0) {
				logger.error("Professor with professorId: " + professor.getUserId() + " not added.");
				throw new ProfessorNotAddedException(professor.getUserId());
			}
			
			logger.info("Professor with professorId: " + professor.getUserId() + " added."); 
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new UserIdAlreadyInUseException(professor.getUserId());
			
		} 
		
	}
	
	/**
	 * Assign courses to Professor using SQL commands
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException 
	 */
	@Override
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{
		
		statement = null;
		try {
			String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setString(2,courseCode);
			int row = statement.executeUpdate();
			
			logger.info(row + " course assigned.");
			if(row == 0) {
				logger.error(courseCode + " not found");
				throw new CourseNotFoundException(courseCode);
			}
			
			logger.info("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");
		
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			throw new UserNotFoundException(professorId);
			
		}
		
	}
	
	/**
	 * View courses in the catalog
	 * @param catalogId
	 * @return List of courses in the catalog
	 */
	public List<Course> viewCourses(int catalogId) {
		
		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {
			
			String sql = SQLQueriesConstants.VIEW_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Course course = new Course();
				course.setCourseCode(resultSet.getString(1));
				course.setCourseName(resultSet.getString(2));
				course.setInstructorId(resultSet.getString(3));
				courseList.add(course);
				
			}
			
			logger.info(courseList.size() + " courses in catalogId: " + catalogId + ".");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		
		return courseList; 
		
	}
	
	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */
	@Override
	public List<Professor> viewProfessors() {
		
		statement = null;
		List<Professor> professorList = new ArrayList<>();
		try {

			String sql = SQLQueriesConstants.VIEW_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Professor professor = new Professor();
				professor.setUserId(resultSet.getString(1));
				professor.setName(resultSet.getString(2));
				professor.setGender(Gender.stringToGender(resultSet.getString(3)));
				professor.setDepartment(resultSet.getString(4));
				professor.setDesignation(resultSet.getString(5));
				professor.setAddress(resultSet.getString(6));
				professor.setCountry(resultSet.getString(7));
				professor.setRole(Role.PROFESSOR);
				professor.setPassword("*********");
				professorList.add(professor);
				
			}
			
			logger.info(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {
			
			logger.error(se.getMessage());
			
		}
		return professorList;
	}

	/**
	 * Update records of Professor
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	@Override
	public void updateProfessor(Professor NewDetails) throws UserDetailsNotUpdatedException {
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_PROFESSOR_QUERY);
			preparedStatement.setString(1, NewDetails.getUserId());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (NewDetails.getName().equals("\n"))
				NewDetails.setName(resultSet.getString(2));

			if (NewDetails.getDepartment().equals("\n"))
				NewDetails.setDepartment(resultSet.getString(4));

			if (NewDetails.getDesignation().equals("\n"))
				NewDetails.setDesignation(resultSet.getString(5));

			if (NewDetails.getAddress().equals("\n"))
				NewDetails.setAddress(resultSet.getString(6));

			if (NewDetails.getCountry().equals("\n"))
				NewDetails.setCountry(resultSet.getString(7));

			PreparedStatement preparedStatementProf = connection.prepareStatement(SQLQueriesConstants.UPDATE_PROFESSOR);
			PreparedStatement preparedStatementUser = connection.prepareStatement(SQLQueriesConstants.UPDATE_USER);


			preparedStatementProf.setString(1, NewDetails.getDepartment());
			preparedStatementProf.setString(2, NewDetails.getDesignation());
			preparedStatementProf.setString(3, NewDetails.getUserId());

			preparedStatementUser.setString(1, NewDetails.getName());
			preparedStatementUser.setString(2, NewDetails.getAddress());
			preparedStatementUser.setString(3, NewDetails.getCountry());
			preparedStatementUser.setString(4, NewDetails.getUserId());

			int row1 = preparedStatementProf.executeUpdate();
			int row2 = preparedStatementUser.executeUpdate();

			if (row1 == 0 || row2 == 0) {
				throw new UserDetailsNotUpdatedException(NewDetails.getUserId());
			}

		} catch (SQLException se) {

			logger.error(se.getMessage());
			System.out.println(se.getMessage());
		}
		return;
	}

	/**
	 * Update records of Student
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	@Override
	public void updateStudent(Student NewDetails) throws UserDetailsNotUpdatedException {
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_QUERY);
			preparedStatement.setString(1, NewDetails.getUserId());
			ResultSet resultSet = preparedStatement.executeQuery();

			if (NewDetails.getName().equals("\n"))
				NewDetails.setName(resultSet.getString(2));

			if (NewDetails.getBranchName().equals("\n"))
				NewDetails.setBranchName(resultSet.getString(4));

			if (NewDetails.getAddress().equals("\n"))
				NewDetails.setAddress(resultSet.getString(5));

			if (NewDetails.getCountry().equals("\n"))
				NewDetails.setCountry(resultSet.getString(6));

			PreparedStatement preparedStatementStud = connection.prepareStatement(SQLQueriesConstants.UPDATE_STUDENT);
			PreparedStatement preparedStatementUser = connection.prepareStatement(SQLQueriesConstants.UPDATE_USER);


			preparedStatementStud.setString(1, NewDetails.getBranchName());
			preparedStatementStud.setString(2, NewDetails.getUserId());

			preparedStatementUser.setString(1, NewDetails.getName());
			preparedStatementUser.setString(2, NewDetails.getAddress());
			preparedStatementUser.setString(3, NewDetails.getCountry());
			preparedStatementUser.setString(4, NewDetails.getUserId());

			int row1 = preparedStatementStud.executeUpdate();
			int row2 = preparedStatementUser.executeUpdate();

			if (row1 == 0 || row2 == 0) {
				throw new UserDetailsNotUpdatedException(NewDetails.getUserId());
			}

		} catch (SQLException se) {

			logger.error(se.getMessage());
			System.out.println(se.getMessage());
			throw new UserDetailsNotUpdatedException(NewDetails.getUserId());
		}
		return;
	}
}
