package com.flipkart.business;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.dao.RegistrationDaoOperation;
import org.apache.log4j.Logger;

public class RegistrationOperation implements RegistrationInterface {

	RegistrationDaoOperation registrationDaoOperation = RegistrationDaoOperation.getInstance();
	private static Logger logger = Logger.getLogger(RegistrationOperation.class);

	private RegistrationOperation() {
	}

	public static RegistrationOperation getInstance() {
		logger.info("Function getInstance called RegistrationOperation");
		return null;
	}

	@Override
	public boolean addCourse(String courseCode, int studentId, List<Course> availableCourseList) {

		boolean isCoursePresent = false;
		boolean ifSuccess;

		for(Course course: availableCourseList)
		{
			if(courseCode.equals(course.getCourseCode()))
			{
				isCoursePresent = true;
			}
		}

		if(!isCoursePresent)
		{
			System.err.println("The selected course is not offered!");
			return false;
		}

		try
		{
			ifSuccess = registrationDaoOperation.addCourse(courseCode,studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}

		return ifSuccess;
	}

	@Override
	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList) {
		boolean isCourseRegistered = false;
		boolean ifSuccess;

		for(Course course: registeredCourseList)
		{
			if(courseCode.equals(course.getCourseCode()))
			{
				isCourseRegistered = true;
			}
		}

		if(!isCourseRegistered)
		{
			System.err.println("The selected course is not registered!");
			return false;
		}

		try
		{
			ifSuccess = registrationDaoOperation.dropCourse(courseCode,studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}

		return ifSuccess;
	}

	@Override
	public double calculateFee(int studentId) {

		double fee;

		try
		{
			fee = registrationDaoOperation.calculateFee(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return 0.0;
		}
		return fee;
	}

	@Override
	public List<GradeCard> viewGradeCard(int studentId) {

		List<GradeCard> GradeCard = null;

		try
		{
			GradeCard = registrationDaoOperation.viewGradeCard(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}

		return GradeCard;
	}

	@Override
	public List<Course> viewCourses(int studentId) {

		List<Course> Courses = null;

		try
		{
			Courses = registrationDaoOperation.viewCourses(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}

		return Courses;
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentId) {

		List<Course> registeredCourses = null;

		try
		{
			registeredCourses = registrationDaoOperation.viewRegisteredCourses(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return null;
		}

		return registeredCourses;
	}

	@Override
	public boolean getRegistrationStatus(int studentId) {

		boolean status = false;

		try
		{
			status = registrationDaoOperation.getRegistrationStatus(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return false;
		}
		return status;
	}

	@Override
	public void setRegistrationStatus(int studentId)
	{

		try
		{
			registrationDaoOperation.setRegistrationStatus(studentId);
		}
		catch(SQLException e)
		{
			System.err.println(e.getMessage());
			return;
		}
	}

}
