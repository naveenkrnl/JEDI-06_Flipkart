package com.flipkart.business;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import com.flipkart.validator.AdminValidator;

import java.util.List;

public class AdminOperation implements AdminInterface {

	private static AdminOperation instance = null;
	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();

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
		System.out.println("Function deleteCourse called from AdminOperation");
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
	public void addProfessor(Professor professor) {
		System.out.println("Function addProfessor called from AdminOperation");
	}

	@Override
	public List<Student> viewPendingAdmissions() {
		System.out.println("Function viewPendingAdmissions called from AdminOperation");
		return null;
	}

	@Override
	public void approveStudent(int studentId, List<Student> studentList) {
		System.out.println("Function approveStudent called from AdminOperation");
	}

	@Override
	public void assignCourse(String courseCode, String professorId) {
		System.out.println("Function assignCourse called from AdminOperation");

	}

	@Override
	public List<Course> viewCourses(int catalogId) {
		System.out.println("Function viewCourses called from AdminOperation");
		return null;
	}

	@Override
	public List<Professor> viewProfessors() {
		System.out.println("Function viewProfessors called from AdminOperation");
		return null;
	}

	public static void main(String[] args) {
		addCourse()
	}
}
