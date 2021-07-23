package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;

/**
 * 
 * Class for Student Validator
 * 
 */
public class StudentValidator {

	public static boolean isRegistered(String courseCode,int studentId,List<Course>registeredCourseList)
	{
		for(Course course : registeredCourseList)
		{
			if(courseCode.equalsIgnoreCase(course.getCourseCode())) 
			{
				return true; 
			}
		}
		
		return false;
	}
	

	public static boolean isValidCourseCode(String courseCode,List<Course>availableCourseList) 
	{
		for(Course course : availableCourseList)
		{
			if(courseCode.equalsIgnoreCase(course.getCourseCode())) 
			{
				return true; 
			}
		}
		
		return false;
	
	}
	

}
