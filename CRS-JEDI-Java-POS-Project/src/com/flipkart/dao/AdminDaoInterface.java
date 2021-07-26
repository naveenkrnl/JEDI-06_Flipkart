package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
// import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
// import com.flipkart.bean.User;

/**
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {

	public boolean createDBRecordAndUpdateObject(Admin admin);

	public Admin getAdminFromUserId(int userId);

	public Admin getAdminFromEmail(String email);

	public List<Student> viewUnapprovedStudents();

	public boolean approveStudent(int userId);

	public void addProfessor(Professor professor);

	public List<Professor> viewProfessors();

	public boolean createCourseDBRecordAndUpdateObject(Course course);

	public Course getCouseFromCourseCodeAndCatalogId(String courseCode, int catalogId);

	public Course getCouseFromCourseId(int courseId);

	public boolean addCourse(Course course);

	public boolean assignCourseToProfessor(int courseId, int professorUserId);

	public boolean deleteCourse(int courseId);

	public List<Course> viewCourses(int courseCatalogId);

}
