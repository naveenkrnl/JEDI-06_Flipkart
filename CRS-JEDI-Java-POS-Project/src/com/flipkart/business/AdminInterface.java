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
	 * Method to demonstrate admin operation performed
	 */
	default public void demonstrate(){
		System.out.println("Admin Operation performed");
	}

	/**
	 * Method to add Admin Account
	 *
	 * @param name : Name of the Admin
	 * @param userID : User ID of the Admin
	 * @param password : password
	 * @param gender : Gender of the Admin
	 * @param address  : Address Of the Admin
	 * @param country : Country Of the Admin
	 * @return Admin ID
	 */
	public int register(String name, String userID, String password, Gender gender, String address,
						String country) throws AdminAccountNotCreatedException;
	/**
	 * Method to Delete Course from Course Catalog
	 * 
	 * @param courseCode : Course Code
	 * @param courseList : Courses available in the catalog
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
	 * @param studentId : Student ID
	 * @param studentList List Of Students
	 */
	public void approveStudent(int studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

	/**
	 * Method to add Professor to DB
	 * 
	 * @param professor : Professor Object storing details of a professor
	 */
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;

	/**
	 * Method to assign Course to a Professor
	 * 
	 * @param courseCode : Course Code
	 * @param professorId : ID Of Professor
	 */
	public void assignCourse(String courseCode, String professorId)
			throws CourseNotFoundException, UserNotFoundException;

	/**
	 * Method to get list of courses in catalog
	 * 
	 * @param catalogId: Id Of Catalog
	 * @return List of courses in catalog
	 */
	public List<Course> viewCourses(int catalogId);

	/**
	 * View professor in the institute
	 * 
	 * @return List of the professors in the institute
	 */
	public List<Professor> viewProfessors();
}
