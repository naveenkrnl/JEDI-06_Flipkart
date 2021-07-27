package com.flipkart.business;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

public interface AdminInterface {

	Admin getAdminFromUserId(int userId) throws UserNotFoundException;

	List<Course> viewCourses(int courseCatalogId);

	boolean addCourse(Course course) throws CourseAlreadyRegisteredException;

	boolean deleteCourse(int courseId);

	List<Student> viewPendingAdmissions();

	boolean approveStudent(int userId);

	@SuppressWarnings("UnusedReturnValue")
    boolean addProfessor(Professor professor) throws UserIdAlreadyInUseException;

	List<Professor> viewProfessorList();

	boolean assignCourseToProfessor(int courseId, int professorUserId);
}
