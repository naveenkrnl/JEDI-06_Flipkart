package com.flipkart.business;


import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

public interface RegistrationInterface {

	public boolean addCourse(String courseCode, int studentId, List<Course> courseList);

	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList);

	public List<Course> viewCourses(int studentId);

	public List<Course> viewRegisteredCourses(int studentId);

	public List<StudentGrade> viewGradeCard(int studentId);

	public double calculateFee(int studentId);

	public boolean getRegistrationStatus(int studentId);

	public void setRegistrationStatus(int studentId);
	
}
