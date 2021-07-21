package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

public class RegistrationOperation implements RegistrationInterface {

	private RegistrationOperation() {
	}

	public static RegistrationOperation getInstance() {
		System.out.println("Function getInstance called RegistrationOperation");
		return null;
	}

	@Override
	public boolean addCourse(String courseCode, int studentId, List<Course> availableCourseList) {
		System.out.println("Function addCourse called RegistrationOperation");
		return false;
	}

	@Override
	public boolean dropCourse(String courseCode, int studentId, List<Course> registeredCourseList) {
		System.out.println("Function dropCourse called RegistrationOperation");
		return false;
	}

	@Override
	public double calculateFee(int studentId) {
		System.out.println("Function calculateFee called RegistrationOperation");
		return 0.0;
	}

	@Override
	public List<GradeCard> viewGradeCard(int studentId) {
		System.out.println("Function viewGradeCard called RegistrationOperation");
		return null;
	}

	@Override
	public List<Course> viewCourses(int studentId) {
		System.out.println("Function viewCourses called RegistrationOperation");
		return null;
	}

	@Override
	public List<Course> viewRegisteredCourses(int studentId) {
		System.out.println("Function viewRegisteredCourses called RegistrationOperation");
		return null;
	}

	@Override
	public boolean getRegistrationStatus(int studentId) {
		System.out.println("Function getRegistrationStatus called RegistrationOperation");
		return true;
	}

	@Override
	public void setRegistrationStatus(int studentId) {
		System.out.println("Function setRegistrationStatus called com.flipkart.business");
	}

}
