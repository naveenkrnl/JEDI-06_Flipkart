package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

import java.util.List;

public interface RegistrationInterface {
	int numOfRegisteredCourses(int studentUserId);

	boolean registerStudentToCourse(int courseId, int studentUserId);

	boolean dropCourse(int courseId, int studentUserId);

	List<Course> viewRegisteredCourses(int studentUserId);

	GradeCard getGradeCardFromStudentUserId(int studentUserId);

	List<Course> viewAvailableCoursesToStudent(int studentUserId);
	// public boolean addCourse(String courseCode, int studentId, List<Course>
	// courseList);

	// public boolean dropCourse(String courseCode, int studentId, List<Course>
	// registeredCourseList);

	// public List<Course> viewCourses(int studentId);

	// public List<Course> viewRegisteredCourses(int studentId);

	// public List<GradeCard> viewGradeCard(int studentId);

	// public double calculateFee(int studentId);

	// public boolean getRegistrationStatus(int studentId);

	// public void setRegistrationStatus(int studentId);

}
