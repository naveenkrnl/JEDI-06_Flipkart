package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.GradeNotAddedException;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * Interface for Professor Operations
 * 
 */
public interface ProfessorInterface {

	/**
	 * Method to grade a Student
	 * 
	 * @param studentId Student ID
	 * @param courseCode Course Code
	 * @param grade Grade
	 * @return boolean indicating if grade is added or not
	 * @throws GradeNotAddedException If course is not Found
	 */
	public boolean addGrade(int studentId, String courseCode, String grade) throws GradeNotAddedException;

	/**
	 * Method to view all the enrolled students
	 * 
	 * @param profId: professor id
	 * @return List of enrolled students
	 * @throws SQLException If course is not Found
	 */
	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException;

	/**
	 * Method to get list of all course a professor is teaching
	 * 
	 * @param profId: professor id
	 * @return List of courses the professor is teaching
	 */
	public List<Course> getCourses(String profId);

	/**
	 * Method to get the professor name with ID
	 * 
	 * @param profId Professor ID
	 * @return Professor name
	 */
	public String getProfessorById(String profId);
}
