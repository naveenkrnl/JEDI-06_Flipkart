package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.*;

/**
 * 
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {

	/**
	 * Method to add student to database
	 *
	 * @param admin: User object containing all the fields for admin
	 * @return Admin id if admin account is created else 0
	 * @throws AdminAccountNotCreatedException If Admin Account can not be created
	 */
	public int addAdmin(User admin) throws AdminAccountNotCreatedException;

	/**
	 * Delete Course using SQL commands
	 * 
	 * @param courseCode Course Code
	 * @throws CourseNotFoundException If course is Not Found
	 * @throws CourseNotDeletedException If Course is Not Deleted
	 */
	public void deleteCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Add Course using SQL commands
	 * 
	 * @param course Course Object
	 * @throws CourseFoundException Course Is not Found
	 */
	public void addCourse(Course course) throws CourseFoundException;

	/**
	 * Fetch Students yet to approved using SQL commands
	 * 
	 * @return List of Students yet to approved
	 */
	public List<Student> viewPendingAdmissions();

	/**
	 * Approve Student using SQL commands
	 * 
	 * @param studentId Student Id
	 * @throws StudentNotFoundForApprovalException If student is not found for approval
	 */
	public void approveStudent(int studentId) throws StudentNotFoundForApprovalException;

	/**
	 * Add professor using SQL commands
	 *
	 * @param professor Professor Object
	 * @throws ProfessorNotAddedException If professor is not Found
	 * @throws UserIdAlreadyInUseException If ProfessId is Already in Use
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Method to add user using SQL commands
	 * 
	 * @param user User Object
	 * @throws UserNotAddedException If User is not added
	 * @throws UserIdAlreadyInUseException If user Id is Already in Use
	 */
	public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Assign courses to Professor using SQL commands
	 * 
	 * @param courseCode Course Code
	 * @param professorId Professor Id
	 * @throws CourseNotFoundException If course is not Found
	 * @throws UserNotFoundException If user is not Found
	 */
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException;

	/**
	 * View courses in the catalog
	 * 
	 * @param catalogId Catalog Id
	 * @return List of courses in the catalog
	 */
	public List<Course> viewCourses(int catalogId);

	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	public List<Professor> viewProfessors();
}
