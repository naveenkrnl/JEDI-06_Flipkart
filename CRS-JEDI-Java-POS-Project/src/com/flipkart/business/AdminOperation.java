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

public class AdminOperation implements AdminInterface {

	private static Logger logger = Logger.getLogger(AdminOperation.class);
	private static volatile AdminOperation instance = null;

	private AdminOperation() {

	}

	public static AdminOperation getInstance() {
		if (instance == null) {
			synchronized (AdminOperation.class) {
				instance = new AdminOperation();
			}
		}
		return instance;
	}

	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();

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

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

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

	@Override
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {

		try {
			adminDaoOperation.addProfessor(professor);
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			throw e;
		}

	}

	@Override
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException {

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		} catch (CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}

	}

	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}

	@Override
	public void updateProfessor(Professor NewDetails) throws UserDetailsNotUpdatedException {

		adminDaoOperation.updateProfessor(NewDetails);
	}

	@Override
	public void updateStudent(Student NewDetails) throws UserDetailsNotUpdatedException {

		adminDaoOperation.updateStudent(NewDetails);
	}
}
