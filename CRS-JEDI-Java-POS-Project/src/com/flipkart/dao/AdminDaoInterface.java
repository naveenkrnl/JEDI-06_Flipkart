package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;
// import com.flipkart.bean.User;

/**
 * Interface for Admin Dao Operations
 *
 */
public interface AdminDaoInterface {

	boolean createDBRecordAndUpdateObject(Admin admin);

	Admin getAdminFromUserId(int userId);

	Admin getAdminFromEmail(String email);

	List<Student> viewUnapprovedStudents();

	boolean approveStudent(int userId);

	void addProfessor(Professor professor);

	List<Professor> viewProfessors();

	boolean createCourseDBRecordAndUpdateObject(Course course);

	Course getCourseFromCourseCodeAndCatalogId(String courseCode, int catalogId);

	Course getCourseFromCourseId(int courseId);

	boolean addCourse(Course course);

	boolean assignCourseToProfessor(int courseId, int professorUserId);

	boolean deleteCourse(int courseId);

	List<Course> viewCourses(int courseCatalogId);

}
