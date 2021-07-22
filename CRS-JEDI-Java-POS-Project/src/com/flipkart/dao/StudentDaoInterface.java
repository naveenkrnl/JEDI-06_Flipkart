package com.flipkart.dao;

import java.sql.SQLException;

import com.flipkart.bean.Student;

/**
 * 
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {
	
	/**
	 * Method to add student to database
	 * @param student: student object containing all the fields
	 * @return true if student is added, else false
	 */
	public int addStudent(Student student);

	public int getStudentId(String userId);

	public boolean isApproved(int studentId);
}