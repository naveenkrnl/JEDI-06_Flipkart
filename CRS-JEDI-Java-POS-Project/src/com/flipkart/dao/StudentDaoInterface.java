package com.flipkart.dao;

import com.flipkart.bean.Student;

/**
 * 
 * Interface for Student Operations
 *
 */
public interface StudentDaoInterface {

	boolean createDBRecordAndUpdateObject(Student student);

	Student getStudentFromUserIdImpl(int userId);

	Student getStudentFromUserId(int userId);

	Student getStudentFromEmail(String email);

	int getStudentUserId(String email);

	boolean isApproved(int userId);
}