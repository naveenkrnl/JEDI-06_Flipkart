package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;

public class StudentOperation implements StudentInterface {

	final StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
	static StudentOperation instance = null;

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
		if (instance == null) {
			instance = new StudentOperation();
		}
		return instance;
	}

	@Override
	public boolean register(Student student) {
		return studentDaoInterface.createDBRecordAndUpdateObject(student);
	}
}
