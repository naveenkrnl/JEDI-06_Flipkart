package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.*;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.exception.*;
import org.apache.log4j.Logger;

import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.validator.AdminValidator;

/**
 * 
 * 
 * Implementations of Admin Operations
 * 
 */
public class AdminOperation implements AdminInterface {

	private static Logger logger = Logger.getLogger(AdminOperation.class);
	private static volatile AdminOperation instance = null;

	private AdminOperation() {

	}

	/**
	 * Method to make AdminOperation Singleton
	 */
	public static AdminOperation getInstance() {
		if (instance == null) {
			synchronized (AdminOperation.class) {
				instance = new AdminOperation();
			}
		}
		return instance;
	}

	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	/**
	 * Method to add Administrative Account
	 *
	 * @param name
	 * @param userID
	 * @param password
	 * @param gender
	 * @param address
	 * @param country
	 * @return Admin ID
	 * @throws AdminAccountNotCreatedException
	 */
	@Override
	public int register(String name, String userID, String password, Gender gender, String address,
						String country) throws AdminAccountNotCreatedException{
		int adminId = 0;
		try {
			// call the DAO class, and add the admin record to the DB
			User admin = new Admin(userID, name, Role.ADMIN, password, gender, address, country);
			adminId = adminDaoOperation.addAdmin(admin) ;

		} catch (AdminAccountNotCreatedException ex) {
			throw ex;
		}
		return adminId;
	}

	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param dropCourseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException
	 */
	@Override
	public void deleteCourse(String dropCourseCode, List<Course> courseList)
			throws CourseNotFoundException, CourseNotDeletedException {

		if (!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
			logger.error("courseCode: " + dropCourseCode + " not present in catalog!");
			throw new CourseNotFoundException(dropCourseCode);
		}

		try {
			adminDaoOperation.deleteCourse(dropCourseCode);
		} catch (CourseNotFoundException | CourseNotDeletedException e) {
			throw e;
		}

	}

	/**
	 * Method to add Course to Course Catalog
	 * 
	 * @param newCourse     : Course object storing details of a course
	 * @param courseList : Courses available in catalog
	 * @throws CourseFoundException
	 */
	@Override
	public void addCourse(Course newCourse, List<Course> courseList) throws CourseFoundException {

		if (!AdminValidator.isValidNewCourse(newCourse, courseList)) {
			logger.error("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
			throw new CourseFoundException(newCourse.getCourseCode());
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		} catch (CourseFoundException e) {
			throw e;
		}

	}

	/**
	 * Method to view Students yet to be approved by Admin
	 * 
	 * @return List of Students with pending admissions
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	/**
	 * Method to approve a Student
	 * 
	 * @param studentId
	 * @param studentList
	 * @throws StudentNotFoundForApprovalException
	 */
	@Override
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

		if (!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
			// logger.error("studentId: " + studentId + " is already
			// approvet/not-present!");
			throw new StudentNotFoundForApprovalException(studentId);
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		} catch (StudentNotFoundForApprovalException e) {
			throw e;
		}

	}

	/**
	 * Method to add Professor to DB
	 * 
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 */
	@Override
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

		try {
			adminDaoOperation.addProfessor(professor);
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			throw e;
		}

	}

	/**
	 * Method to assign Course to a Professor
	 * 
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 */
	@Override
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException {

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		} catch (CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}

	}

	/**
	 * Method to get list of courses in catalog
	 * 
	 * @param catalogId
	 * @return List of courses in catalog
	 */
	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}

	/**
	 * Update records of Professor
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	@Override
	public void updateProfessor(Professor NewDetails) throws UserDetailsNotUpdatedException {

		adminDaoOperation.updateProfessor(NewDetails);
	}

	/**
	 * Update records of Student
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	@Override
	public void updateStudent(Student NewDetails) throws UserDetailsNotUpdatedException {

		adminDaoOperation.updateStudent(NewDetails);
	}
}
