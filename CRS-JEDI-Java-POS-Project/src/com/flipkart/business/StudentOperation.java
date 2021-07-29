package com.flipkart.business;

import org.apache.log4j.Logger;

import com.flipkart.bean.Student;
import com.flipkart.application.CRSApplication;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * 
 * 
 * Implementations of Student Operations
 *
 */
public class StudentOperation implements StudentInterface {

	private static volatile StudentOperation instance = null;
	private static Logger logger = Logger.getLogger(CRSApplication.class);
	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	private StudentOperation() {

	}

	/**
	 * Method to make StudentOperation Singleton
	 * 
	 * @return StudentOperation Instance
	 */
	public static StudentOperation getInstance() {
		if (instance == null) {
			synchronized (StudentOperation.class) {
				instance = new StudentOperation();
			}
		}
		return instance;
	}

	/**
	 * Method to register a student, although student can't login until it's
	 * approved by admin
	 * 
	 * @param name     Name
	 * @param userId   User ID
	 * @param password Password
	 * @param gender   Gender
	 * @param batch    Batch number
	 * @param branch   Branch
	 * @param address  Address of the student
	 * @param country  Country Of the student
	 * @return Student ID
	 */
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

	/**
	 * Method to get Student ID from User ID
	 * 
	 * @param userId User ID
	 * @return Student ID
	 */
	@Override
	public int getStudentId(String userId) {
		return studentDaoInterface.getStudentId(userId);

	}

	/**
	 * Method to check if student is approved by Admin or not
	 * 
	 * @param studentId Student ID
	 * @return boolean indicating if student is approved
	 */
	@Override
	public boolean isApproved(int studentId) {
		return studentDaoInterface.isApproved(studentId);
	}

}
