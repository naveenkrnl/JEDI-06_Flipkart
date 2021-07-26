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
 * Implementation of admin operations.
 * 
 */
public class AdminOperation implements AdminInterface {

	private static Logger logger = Logger.getLogger(AdminOperation.class);
	private static volatile AdminOperation instance = null;

	private AdminOperation() {

	}

	/**
	 * Obtain an object of the class.
	 *
	 * @return Instance of the class
	 */
	public static AdminOperation getInstance() {
		if (instance == null) {
			synchronized (AdminOperation.class) {
				instance = new AdminOperation();
			}
		}
		return instance; // singleton structure
	}

	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	/**
	 * Add an administrative account
	 *
	 * @param name Name of the admin
	 * @param userID User ID of the admin
	 * @param password Password of the admin
	 * @param gender Gender of the admin
	 * @param address Address of the admin
	 * @param country Country of the admin
	 * @return Admin ID
	 */
	@Override
	public int register(String name, String userID, String password, Gender gender, String address,
						String country) throws AdminAccountNotCreatedException{
		int adminId = 0;
		try {
			User admin = new Admin(userID, name, Role.ADMIN, password, gender, address, country);
			adminId = adminDaoOperation.addAdmin(admin) ;

		} catch (AdminAccountNotCreatedException ex) {
			throw ex;
		}
		return adminId;
	}

	/**
	 * Delete a course from the course catalogue.
	 * 
	 * @param dropCourseCode Course code of the course to be dropped
	 * @param courseList : List of all courses available in the catalogue
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
	 * Add a course to the course catalogue.
	 * 
	 * @param newCourse : Store the details of the course to be added
	 * @param courseList : List of all courses available in catalogue
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
	 * View all students yet to be approved by the admin.
	 * 
	 * @return List of all students with pending admission.
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	/**
	 * Approve a Student for successful registration.
	 *
	 * @param studentId : Student ID
	 * @param studentList List Of students
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
	 * Add a professor to the database.
	 *
	 * @param professor : Store the details of the professor to be added
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
	 * Assign a course to a professor.
	 *
	 * @param courseCode : Course code
	 * @param professorId : ID of the professor
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
	 * Get a list of all courses in the catalogue.
	 *
	 * @param catalogId: ID of the catalogue
	 * @return List of courses in the catalogue
	 */
	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	/**
	 * View a list of all the professors in the institute.
	 *
	 * @return List of all professors in the institute
	 */
	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}

}
