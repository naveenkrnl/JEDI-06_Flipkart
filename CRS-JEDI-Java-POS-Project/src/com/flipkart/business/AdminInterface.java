package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.exception.*;

/**
 * 
 * 
 * Interface for Admin Operations
 * 
 */
public interface AdminInterface {

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
	public int register(String name, String userID, String password, Gender gender, String address,
						String country) throws AdminAccountNotCreatedException;
	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException
	 * @throws CourseNotDeletedException
	 */
	public void deleteCourse(String courseCode, List<Course> courseList)
			throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Method to add Course to Course Catalog
	 * 
	 * @param course     : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 */
	public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

	/**
	 * Method to view Students yet to be approved by Admin
	 * 
	 * @return List of Students with pending admissions
	 */
	public List<Student> viewPendingAdmissions();

	/**
	 * Method to approve a Student
	 * 
	 * @param studentId
	 * @param studentList
	 * @throws StudentNotFoundForApprovalException
	 */
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

	/**
	 * Method to add Professor to DB
	 * 
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 * @throws UserIdAlreadyInUseException
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Method to assign Course to a Professor
	 * 
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException
	 */
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException;

	/**
	 * Method to get list of courses in catalog
	 * 
	 * @param catalogId
	 * @return List of courses in catalog
	 */
	public List<Course> viewCourses(int catalogId);

	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	public List<Professor> viewProfessors();

	/**
	 * Update records of Professor
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	public void updateProfessor(Professor NewDetails) throws UserDetailsNotUpdatedException;

	/**
	 * Update records of Student
	 *
	 * @param NewDetails
	 * @return boolean
	 */
	public void updateStudent(Student NewDetails) throws UserDetailsNotUpdatedException;
}
