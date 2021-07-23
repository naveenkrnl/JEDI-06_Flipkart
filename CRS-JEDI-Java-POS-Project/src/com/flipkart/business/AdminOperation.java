package com.flipkart.business;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.validator.AdminValidator;
import org.apache.log4j.Logger;

import java.util.List;

public class AdminOperation implements AdminInterface {

	private static AdminOperation instance = null;
	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	private static Logger logger = Logger.getLogger(AdminOperation.class);

	public static AdminOperation getInstance()
	{
		if(instance == null)
		{
				instance = new AdminOperation();
		}
		return instance;
	}


	@Override
	public void deleteCourse(String courseCode, List<Course> courseList) {
		logger.info("Function deleteCourse called from AdminOperation");
	}

	@Override
	public void addCourse(Course newCourse, List<Course> courseList) {

		if(AdminValidator.isValidNewCourse(newCourse,courseList)){
			System.err.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
		}

		try {
			adminDaoOperation.addCourse(newCourse);
		}
		catch(Exception e) {
			throw e;
		}


	}

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}

	@Override
	public void approveStudent(String studentId, List<Student> studentList) {

		if(!AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
			System.err.println("studentId: " + studentId + " is already approvet/not-present!");
			return;
		}

		try {
			adminDaoOperation.approveStudent(studentId);
		}
		catch(Exception e) {
			throw e;
		}

	}

	@Override
	public void addProfessor(Professor professor) {

		try {
			adminDaoOperation.addProfessor(professor);
		}
		catch(Exception e) {
			throw e;
		}

	}

	@Override
	public void assignCourse(String courseCode, String professorId){

		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		}
		catch(Exception e) {
			throw e;
		}

	}

	@Override
	public List<Course> viewCourses(int catalogId) {

		return adminDaoOperation.viewCourses(catalogId);

	}

	@Override
	public List<Professor> viewProfessors() {

		return adminDaoOperation.viewProfessors();

	}
}