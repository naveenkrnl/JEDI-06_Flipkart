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
	 *
	 * @return instance of AdminOperation
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
	 * @param name     Name
	 * @param userID   User ID
	 * @param password Password
	 * @param gender   Gender
	 * @param address  Address
	 * @param country  Country
	 * @return Admin ID
	 */
	@Override
	public int register(String name, String userID, String password, Gender gender, String address, String country)
			throws AdminAccountNotCreatedException {
		int adminId = 0;
		try {
			User admin = new Admin(userID, name, Role.ADMIN, password, gender, address, country);
			adminId = adminDaoOperation.addAdmin(admin);

		} catch (AdminAccountNotCreatedException ex) {
			throw ex;
		}
		return adminId;
	}

	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param dropCourseCode Course Code which is to be dropped
	 * @param courseList     : Courses available in the catalog
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
	 * @param newCourse  : Course object storing details of a course
	 * @param courseList : Courses available in catalog
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
	 * @param studentId   Student ID
	 * @param studentList List Of Students
	 */
	@Override
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {

		if (!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
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
	 * @param courseCode  Course Code
	 * @param professorId Professor ID
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
	 * @param catalogId Catalog ID
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

}
