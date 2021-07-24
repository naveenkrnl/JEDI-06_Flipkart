package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.RegisteredCourse;

/**
 * Interface for Registration DAO Operation
 *
 */
public interface RegistrationDaoInterface {

	public boolean createRegisteredCourseDBRecordAndUpdateObject(RegisteredCourse registeredCourse);

	public RegisteredCourse getRegisteredCourseFromStudentIdAndCourseId(int studentUserId, int courseId);

	public RegisteredCourse getRegisteredCourseFromRegisteredCourseId(int registeredCourseId);

	public boolean registerStudentToCourse(int courseId, int studentUserId);

	public int numOfRegisteredCourses(int studentUserId);

	public boolean seatAvailable(int courseId);

	public boolean isStudentAlreadyRegisteredToCourseId(int courseId, int studentUserId);

	public boolean dropCourseFromCourseIdAndStudentId(int courseId, int studentUserId);

	public double calculateFeeFromStudentUserId(int studentUserId);

	public GradeCard getGradeCardFromStudentUserId(int studentUserId);

	public List<Course> viewAvailableCoursesToStudent(int studentUserId);

	public List<Course> viewRegisteredCoursesForStudent(int studentUserId);
}