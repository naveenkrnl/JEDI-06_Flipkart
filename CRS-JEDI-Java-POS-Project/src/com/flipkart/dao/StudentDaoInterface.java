package com.flipkart.dao;

import java.sql.SQLException;

import com.flipkart.bean.Student;

/**
 * 
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {

	public int getStudentUserId(String userId);

	public boolean isApproved(int studentId);
}