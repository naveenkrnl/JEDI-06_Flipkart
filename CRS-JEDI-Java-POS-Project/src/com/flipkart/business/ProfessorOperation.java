package com.flipkart.business;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;


public class ProfessorOperation implements ProfessorInterface {
	

	private ProfessorOperation()
	{

	}

	public static ProfessorOperation getInstance()
	{
	}
	

	@Override
	public boolean addGrade(int studentId,String courseCode,String grade) {
	}
	

	@Override
	public List<RegisteredCourseStudent> viewEnrolledStudents(String profId){
	}

	@Override
	public List<Course> getCourses(String profId) {
	}
	

	@Override
	public String getProfessorById(String profId)
	{
	}
}
