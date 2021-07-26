package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {

	boolean register(Student student);

	int getStudentId(String userId);

	boolean isApproved(int studentId);

	Student getStudentFromStudentUserId(int studentUserId);

	Student getStudentFromEmail(String email);
}
