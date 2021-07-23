package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

public interface AdminInterface {

	public void deleteCourse(String courseCode, List<Course> courseList);

	public void addCourse(Course course, List<Course> courseList);

	public List<Student> viewPendingAdmissions();

	public void approveStudent(String studentId, List<Student> studentList);

	public void addProfessor(Professor professor);

	public void assignCourse(String courseCode, String professorId);

	public List<Course> viewCourses(int catalogId);

	public List<Professor> viewProfessors();
}
