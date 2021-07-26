package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;

public interface StudentInterface {

	public boolean register(Student student);

	public int getStudentId(String userId);

	public boolean isApproved(int studentId);

	public Student getStudentFromStudentUserId(int studentUserId);

	public Student getStudentFromEmail(String email);
}
