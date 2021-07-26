package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * 
 * 
 * Interface for Registration Operation
 * 
 */
public interface RegistrationInterface {

	/**
	 * Method to add Course selected by student
	 * 
	 * @param courseCode Course Code
	 * @param studentId Student ID
	 * @param courseList List Of Courses
	 * @return boolean indicating if the course is added successfully
	 */
	public boolean addCourse(String courseCode, int studentId, List<Course> courseList)
			throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException;

	/**
	 * Method to drop Course selected by student
	 * 
	 * @param courseCode Course Code
	 * @param studentId Student ID
	 * @param registeredCourseList List Of Registered Courses
	 * @return boolean indicating if the course is dropped successfully
	 */
	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList)
			throws CourseNotFoundException, SQLException;

	/**
	 * Method to view the list of available courses
	 * 
	 * @param studentId Student ID
	 * @return List of courses
	 */
	public List<Course> viewCourses(int studentId) throws SQLException;

	/**
	 * Method to view the list of courses registered by the student
	 * 
	 * @param studentId Student ID
	 * @return List of courses
	 */
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;

	/**
	 * Method to view grade card for students
	 * 
	 * @param studentId Student ID
	 * @return List of Student's Grades
	 */
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;

	/**
	 * Method for Fee Calculation for selected courses Fee calculation for selected
	 * courses
	 * 
	 * @param studentId Student ID
	 * @return Fee Student has to pay
	 */
	public double calculateFee(int studentId) throws SQLException;

	/**
	 * Method to check student registration status
	 * 
	 * @param studentId Student ID
	 * @return boolean indicating if the student's registration status
	 */
	public boolean getRegistrationStatus(int studentId) throws SQLException;

	/**
	 * Method to set student registration status
	 * 
	 * @param studentId Student ID
	 */
	public void setRegistrationStatus(int studentId) throws SQLException;

}
