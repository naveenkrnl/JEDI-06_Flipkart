package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminInterface {

	Admin getAdminFromUserId(int userId);

	List<Course> viewCourses(int courseCatalogId);

	boolean addCourse(Course course);

	boolean deleteCourse(int courseId);

	List<Student> viewPendingAdmissions();

	boolean approveStudent(int userId);

	@SuppressWarnings("UnusedReturnValue")
	boolean addProfessor(Professor professor);

	List<Professor> viewProfessorList();

	boolean assignCourseToProfessor(int courseId, int professorUserId);
}
