package com.flipkart.dao;

import com.flipkart.bean.Student;

/**
 * 
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {

	public boolean createDBRecordAndUpdateObject(Student student);

	public Student getStudentFromUserIdImpl(int userId);

	public Student getStudentFromUserId(int userId);

	public Student getStudentFromEmail(String email);

	public int getStudentUserId(String email);

	public boolean isApproved(int userId);
}