package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.dao.*;

import java.util.List;

public class AdminOperation implements AdminInterface {

	private static AdminOperation instance = null;
	AdminDaoInterface adminDaoOperation = AdminDaoOperation.getInstance();
	ProfessorDaoInterface professorDaoOperation = ProfessorDaoOperation.getInstance();
	UserDaoInterface userDaoOperation = UserDaoOperation.getInstance();

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
		if (adminDaoOperation.getCouseFromCourseCodeAndCatalogId(course.getCourseCode(),
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

	// // TODO : Fix me
	// @Override
	// public void deleteCourse(String courseCode, List<Course> courseList) {
	// System.out.println("Function deleteCourse called from AdminOperation");
	// }

	// // TODO : Fix me
	// @Override
	// public void addCourse(Course newCourse, List<Course> courseList) {

	// if (AdminValidator.isValidNewCourse(newCourse, courseList)) {
	// System.err.println("courseCode: " + newCourse.getCourseCode() + " already
	// present in catalog!");
	// }

	// try {
	// AdminDaoOperation.getInstance().createCourseDBRecordAndUpdateObject(newCourse);
	// } catch (Exception e) {
	// throw e;
	// }
	// }

	// @Override
	// public List<Student> viewPendingAdmissions() {
	// return adminDaoOperation.viewUnapprovedStudents();
	// }

	// @Override
	// public void approveStudent(int studentId, List<Student>
	// unapprovedStudentList) {

	// if (!AdminValidator.isValidUnapprovedStudent(studentId,
	// unapprovedStudentList)) {
	// System.err.println("studentId: " + studentId + " is already
	// approvet/not-present!");
	// // TODO : User is already approved
	// return;
	// }

	// try {
	// adminDaoOperation.approveStudent(studentId);
	// } catch (Exception e) {
	// throw e;
	// }

	// }

	// @Override
	// public void addProfessor(Professor professor) {

	// try {
	// adminDaoOperation.addProfessor(professor);
	// } catch (Exception e) {
	// throw e;
	// }

	// }

	// @Override
	// public void assignCourse(int courseCode, String professorId) {

	// try {
	// adminDaoOperation.assignCourseToProfessor(courseCode, professorId);
	// } catch (Exception e) {
	// throw e;
	// }
	// }

	// @Override
	// public List<Course> viewCourses(int catalogId) {
	// return adminDaoOperation.viewCourses(catalogId);
	// }

	// @Override
	// public List<Professor> viewProfessors() {
	// return adminDaoOperation.viewProfessors();
	// }
}