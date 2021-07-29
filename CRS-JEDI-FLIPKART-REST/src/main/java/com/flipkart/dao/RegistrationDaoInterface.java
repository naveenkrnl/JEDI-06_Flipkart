/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.exception.CourseNotFoundException;

/**
 * 
 * Interface for Registration DAO Operation
 *
 */
public interface RegistrationDaoInterface {

	/**
	 * Method to add course in database
	 * 
	 * @param courseCode Course Code
	 * @param studentId  Student Id
	 * @return boolean indicating if the course is added successfully
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public boolean addCourse(String courseCode, int studentId) throws SQLException;

	/**
	 * Drop Course selected by student
	 * 
	 * @param courseCode Course Code
	 * @param studentId  Student Id
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException if course does not exists
	 * @throws SQLException            If a error occurs while accessing the
	 *                                 database
	 */
	public boolean dropCourse(String courseCode, int studentId) throws SQLException, CourseNotFoundException;

	/**
	 * Method to get the list of courses available from course catalogue
	 * 
	 * @param studentId Student Id
	 * @return list of Courses available in Course Catalogue
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public List<Course> viewCourses(int studentId) throws SQLException;

	/**
	 * Method to View list of Registered Courses
	 * 
	 * @param studentId Student Id
	 * @return list of Registered Courses of the student
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;

	/**
	 * Method to view grade card of the student
	 * 
	 * @param studentId Student Id
	 * @return Grade Card of the student
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public List<StudentGrade> viewGradeCard(int studentId) throws SQLException;

	/**
	 * Method to retrieve fees for the selected courses from the database and
	 * calculate total fees
	 * 
	 * @param studentId Student Id
	 * @return Fees Student has to pay
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public double calculateFee(int studentId) throws SQLException;

	/**
	 * Check if seat is available for that particular course
	 * 
	 * @param courseCode Course Code
	 * @return seat availability status of the given course
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public boolean seatAvailable(String courseCode) throws SQLException;

	/**
	 * Method to get the list of courses registered by the student Number of
	 * registered courses for a student
	 * 
	 * @param studentId Student Id
	 * @return Number of registered Courses
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public int numOfRegisteredCourses(int studentId) throws SQLException;

	/**
	 * Method checks if the student is registered for that course
	 * 
	 * @param courseCode Course Code
	 * @param studentId  Student Id
	 * @return Student's registration status for the given course
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public boolean isRegistered(String courseCode, int studentId) throws SQLException;

	/**
	 * Method to get student registration status
	 * 
	 * @param studentId Student Id
	 * @return Student's registration status for the semester
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public boolean getRegistrationStatus(int studentId) throws SQLException;

	/**
	 * Method to set student registration status
	 * 
	 * @param studentId Student Id
	 * @throws SQLException If a error occurs while accessing the database
	 */
	public void setRegistrationStatus(int studentId) throws SQLException;

}