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

	public boolean registerStudentToCourse(int courseId, int studentUserId) throws UserNotFoundException,
			CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException;

	public boolean dropCourse(int courseId, int studentUserId) throws UserNotFoundException, CourseNotFoundException;

	public List<Course> viewRegisteredCourses(int studentUserId) throws UserNotFoundException;

	public List<Course> viewAvailableCoursesToStudent(int studentUserId) throws UserNotFoundException;

	public GradeCard getGradeCardFromStudentUserId(int studentUserId) throws UserNotFoundException;

}
