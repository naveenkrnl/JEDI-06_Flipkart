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

}
