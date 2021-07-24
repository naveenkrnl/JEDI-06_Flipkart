package com.flipkart.dao;

import java.util.List;

// import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
// import com.flipkart.bean.User;

/**
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {

	public List<Student> viewPendingAdmissions();

	public void approveStudent(int studentId);

	public void addProfessor(Professor professor);

	public List<Professor> viewProfessors();

	// public void deleteCourse(String courseCode);

	// public void addCourse(Course course);

	// public void assignCourse(String courseCode, String professorId);

	// public List<Course> viewCourses(int catalogId);

}
