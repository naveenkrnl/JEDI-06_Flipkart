package com.flipkart.dao;

import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Student;

/**
 *
 * Interface for Admin Dao Operations
 * 
 */
public interface ProfessorDaoInterface {

	/**
	 * Method to get Courses by Professor Id using SQL Commands
	 * 
	 * @param userId, prof id of the professor
	 * @return get the courses offered by the professor.
	 */
	public List<Course> getCoursesByProfessor(String userId);

	/**
	 * Method to view list of enrolled Students using SQL Commands
	 *
	 * @param profId: professor id
	 * @return return the enrolled students for the corresponding professor and
	 */
	public List<EnrolledStudent> getEnrolledStudents(String profId);

	/**
	 * Method to Grade a student using SQL Commands
	 *
	 * @param studentId:  professor id
	 * @param courseCode: course code for the corresponding
	 * @param grade:      grade to be added
	 * @return returns the status after adding the grade
	 */
	public Boolean addGrade(int studentId, String courseCode, String grade);

	/**
	 * Method to Get professor name by id
	 * 
	 * @param profId professor id
	 * @return Professor Id in string
	 */
	public String getProfessorById(String profId);
}
