package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.exception.UserNotFoundException;

import java.util.List;

public interface RegistrationInterface {
	int numOfRegisteredCourses(int studentUserId);

	boolean registerStudentToCourse(int courseId, int studentUserId) throws UserNotFoundException,
			CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException;

	boolean dropCourse(int courseId, int studentUserId) throws UserNotFoundException, CourseNotFoundException;

	List<Course> viewRegisteredCourses(int studentUserId) throws UserNotFoundException;

	List<Course> viewAvailableCoursesToStudent(int studentUserId) throws UserNotFoundException;

	GradeCard getGradeCardFromStudentUserId(int studentUserId) throws UserNotFoundException;

	double calculateFeeFromStudentUserId(int studentUserId);

}
