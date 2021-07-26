package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

public interface RegistrationInterface {
	public int numOfRegisteredCourses(int studentUserId);

	public boolean registerStudentToCourse(int courseId, int studentUserId);

	public boolean dropCourse(int courseId, int studentUserId);

	public List<Course> viewRegisteredCourses(int studentUserId);

	public GradeCard getGradeCardFromStudentUserId(int studentUserId);

	public List<Course> viewAvailableCoursesToStudent(int studentUserId);
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
