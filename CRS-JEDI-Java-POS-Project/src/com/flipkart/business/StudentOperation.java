package com.flipkart.business;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.application.CRSApplication;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.StudentNotRegisteredException;

public class StudentOperation implements StudentInterface {

	private static volatile StudentOperation instance = null;
	private static Logger logger = Logger.getLogger(CRSApplication.class);
	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	private StudentOperation() {

	}

	public static StudentOperation getInstance() {
		if (instance == null) {
			synchronized (StudentOperation.class) {
				instance = new StudentOperation();
			}
		}
		return instance;
	}

	@Override
	public int register(String name, String userId, String password, Gender gender, int batch, String branch,
			String address, String country) throws StudentNotRegisteredException {
		int studentId;
		try {
			Student newStudent = new Student(userId, name, Role.STUDENT, password, gender, address, country, branch, 0,
					batch, false);
			studentId = studentDaoInterface.addStudent(newStudent);

		} catch (StudentNotRegisteredException ex) {
			throw ex;
		}
		return studentId;
	}

	@Override
	public int getStudentId(String userId) {
		return studentDaoInterface.getStudentId(userId);

	}

	@Override
	public boolean isApproved(int studentId) {
		return studentDaoInterface.isApproved(studentId);
	}

}
