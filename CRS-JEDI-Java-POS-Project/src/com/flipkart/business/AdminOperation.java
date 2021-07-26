package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;

import java.util.List;

public class AdminOperation implements AdminInterface {

	private static AdminOperation instance = null;
	final AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	final ProfessorDaoInterface professorDaoOperation = ProfessorDaoOperation.getInstance();
	final UserDaoInterface userDaoOperation = UserDaoOperation.getInstance();

	public static AdminOperation getInstance() {
		if (instance == null) {
			instance = new AdminOperation();
		}
		return instance;
	}

	@Override
	public Admin getAdminFromUserId(int userId) {
		Admin admin = adminDaoOperation.getAdminFromUserId(userId);
		if (admin == null) {
			// throw User not found
		}
		return admin;
	}

	@Override
	public List<Course> viewCourses(int courseCatalogId) {
		return adminDaoOperation.viewCourses(courseCatalogId);
	}

	@Override
	public boolean addCourse(Course course) {
		if (adminDaoOperation.getCourseFromCourseCodeAndCatalogId(course.getCourseCode(),
				course.getCourseCatalogId()) != null) {
			// throw CourseFoundException
		}
		return adminDaoOperation.addCourse(course);
	}

	@Override
	public boolean deleteCourse(int courseId) {
		return adminDaoOperation.deleteCourse(courseId);
	}

	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewUnapprovedStudents();
	}

	@Override
	public boolean approveStudent(int userId) {
		return adminDaoOperation.approveStudent(userId);
	}

	@Override
	public boolean addProfessor(Professor professor) {
		if (userDaoOperation.getUserFromUserId(professor.getUserId()) != null) {
			// throw UserIdInUse;
		}
		return professorDaoOperation.createDBRecordAndUpdateObject(professor);
	}

	@Override
	public List<Professor> viewProfessorList() {
		return adminDaoOperation.viewProfessors();
	}

	@Override
	public boolean assignCourseToProfessor(int courseId, int professorUserId) {
		return adminDaoOperation.assignCourseToProfessor(courseId, professorUserId);
	}
}