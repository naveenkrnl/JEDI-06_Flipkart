package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {

	public Admin getAdminFromUserId(int userId);

	public List<Course> viewCourses(int courseCatalogId);

	public boolean addCourse(Course course);

	public boolean deleteCourse(int courseId);

	public List<Student> viewPendingAdmissions();

	public boolean approveStudent(int userId);

	public boolean addProfessor(Professor professor);

	public List<Professor> viewProfessorList();

	public boolean assignCourseToProfessor(int courseId, int professorUserId);
	// public void deleteCourse(String courseCode, List<Course> courseList);

	// public void addCourse(Course course, List<Course> courseList);

	// public List<Student> viewPendingAdmissions();

	// public void approveStudent(int studentId, List<Student>
	// unapprovedStudentList);

	// public boolean assignCourse(int courseId, int professorUserId);

	// public void assignCourse(String courseCode, String professorId);

	// public List<Course> viewCourses(int catalogId);

	// public List<Professor> viewProfessors();
}
