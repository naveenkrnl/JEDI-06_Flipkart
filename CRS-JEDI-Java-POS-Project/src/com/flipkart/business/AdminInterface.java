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
 * Interface for admin operations.
 * 
 */
public interface AdminInterface {

	/**
	 * Demonstrate admin operation performed.
	 */
	default public void demonstrate(){
		System.out.println("Admin Operation performed");
	}

	/**
	 * Add admin account to database.
	 *
	 * @param name : Name of the Admin
	 * @param userID : User ID of the Admin
	 * @param password : Password of the Admin
	 * @param gender : Gender of the Admin
	 * @param address  : Address Of the Admin
	 * @param country : Country Of the Admin
	 * @throws AdminAccountNotCreatedException If admin account is not created
	 * @return Admin ID
	 */
	public int register(String name, String userID, String password, Gender gender, String address,
						String country) throws AdminAccountNotCreatedException;
	/**
	 * Delete course from course catalogue.
	 * 
	 * @param courseCode : Course Code
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException If course is not found
	 * @throws CourseNotDeletedException If course not deleted
	 */
	public void deleteCourse(String courseCode, List<Course> courseList)
			throws CourseNotFoundException, CourseNotDeletedException;

	/**
	 * Add course to course catalog.
	 * 
	 * @param course     : Store the details of a course
	 * @param courseList : Courses available in the catalog
	 * @throws CourseFoundException If course is not found
	 */
	public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

	/**
	 * View students yet to be approved by admin.
	 * 
	 * @return List of students with pending admission
	 */
	public List<Student> viewPendingAdmissions();

	/**
	 * Approve a Student for successful registration.
	 * 
	 * @param studentId : Student ID
	 * @param studentList List Of students
	 * @throws StudentNotFoundForApprovalException If student is not found
	 */
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

	/**
	 * Add a professor to the database.
	 * 
	 * @param professor : Store the details of the professor to be added
	 * @throws ProfessorNotAddedException If professor is not found
	 * @throws UserIdAlreadyInUseException If user id is already in use
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Assign a course to a professor.
	 * 
	 * @param courseCode : Course code
	 * @param professorId : ID of the professor
	 * @throws CourseNotFoundException If course is not found
	 * @throws UserNotFoundException If user is not found
	 */
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException;

	/**
	 * Get a list of all courses in the catalogue.
	 * 
	 * @param catalogId: ID of the catalogue
	 * @return List of courses in the catalogue
	 */
	public List<Course> viewCourses(int catalogId);

	/**
	 * View a list of all the professors in the institute.
	 * 
	 * @return List of all professors in the institute
	 */
	public List<Professor> viewProfessors();
}
