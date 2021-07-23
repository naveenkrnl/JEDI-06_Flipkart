package com.flipkart.business;

import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;
import org.apache.log4j.Logger;

public class StudentOperation implements StudentInterface {

	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();
	private static Logger logger = Logger.getLogger(StudentOperation.class);
	private StudentOperation() {

	}

	public static StudentOperation getInstance() {
		logger.info("Function getInstance called from StudentOperation");
		return new StudentOperation();
	}

	public int register(String name, String userId, String password, Gender gender, int batch, String branch,
			String address, String country) {

		int id = getStudentId(userId);
		if (id != 0)
		{
			logger.info("Student already registered");
			return 0;
		}

		int studentId = studentDaoInterface.addStudent(new Student(userId, name, Role.STUDENT, password, gender, address, country,
				branch, "", batch, true));

		if (studentId == 0)
			logger.error("Student cannot be registered");

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
