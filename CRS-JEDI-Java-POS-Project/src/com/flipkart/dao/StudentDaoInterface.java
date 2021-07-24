package com.flipkart.dao;

/**
 * 
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {

	public int getStudentUserId(String userId);

	public boolean isApproved(int studentId);
}