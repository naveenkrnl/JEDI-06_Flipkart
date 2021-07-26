package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;

public class StudentOperation implements StudentInterface {

	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	private StudentOperation() {

	}

	@Override
	public Student getStudentFromStudentUserId(int studentUserId) {
		return studentDaoInterface.getStudentFromUserId(studentUserId);
	}

	@Override
	public Student getStudentFromEmail(String email) {
		return studentDaoInterface.getStudentFromEmail(email);
	}

	public static StudentOperation getInstance() {
		System.out.println("Function getInstance called from StudentOperation");
		return new StudentOperation();
	}

	@Override
	public boolean register(Student student) {
		return studentDaoInterface.createDBRecordAndUpdateObject(student);
	}

	@Override
	public int getStudentId(String userId) {
		System.out.println("Function getStudentId called from StudentOperation");
		return 0;

	}

	@Override
	public boolean isApproved(int studentId) {
		System.out.println("Function isApproved called from StudentOperation");
		return true;
	}
}
