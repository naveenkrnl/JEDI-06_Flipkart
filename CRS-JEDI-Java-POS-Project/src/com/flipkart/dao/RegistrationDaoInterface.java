package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.RegisteredCourse;

import java.util.List;

/**
 * Interface for Registration DAO Operation
 *
 */
public interface RegistrationDaoInterface {

	boolean createRegisteredCourseDBRecordAndUpdateObject(RegisteredCourse registeredCourse);

	RegisteredCourse getRegisteredCourseFromStudentIdAndCourseId(int studentUserId, int courseId);

	RegisteredCourse getRegisteredCourseFromRegisteredCourseId(int registeredCourseId);

	boolean registerStudentToCourse(int courseId, int studentUserId);

	int numOfRegisteredCourses(int studentUserId);

	boolean seatAvailable(int courseId);

	boolean isStudentAlreadyRegisteredToCourseId(int courseId, int studentUserId);

	boolean dropCourseFromCourseIdAndStudentId(int courseId, int studentUserId);

	double calculateFeeFromStudentUserId(int studentUserId);

	GradeCard getGradeCardFromStudentUserId(int studentUserId);

	List<Course> viewAvailableCoursesToStudent(int studentUserId);

	List<Course> viewRegisteredCoursesForStudent(int studentUserId);
}