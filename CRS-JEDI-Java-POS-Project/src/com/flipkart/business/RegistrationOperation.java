package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;



public class RegistrationOperation implements RegistrationInterface {


	private RegistrationOperation() {
	}

	public static RegistrationOperation getInstance() {
	}

	@Override
	public boolean addCourse(String courseCode, int studentId,List<Course> availableCourseList) {
	}

	@Override
	public boolean dropCourse(String courseCode, int studentId,List<Course> registeredCourseList){
	}

	@Override
	public double calculateFee(int studentId) {
	}

	@Override
	public List<GradeCard> viewGradeCard(int studentId) {
	}


	@Override
	public List<Course> viewCourses(int studentId) {
	}


	@Override
	public List<Course> viewRegisteredCourses(int studentId){
	}
    

	@Override
	public boolean getRegistrationStatus(int studentId){
	}

	@Override
	public void setRegistrationStatus(int studentId) {
	}

}
