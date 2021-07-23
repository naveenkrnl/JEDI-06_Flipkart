package com.flipkart.validator;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;
import com.flipkart.constant.Grade;


/**
 * 
 * Class for Professor Validator
 * 
 */
public class ProfessorValidator {

	public static boolean isValidStudent(List<RegisteredCourseStudent> students, String studentId)
	{
		boolean result=false;
		//check if student exist in ihe students list
		for(int i=0;i<students.size();i++)
		{
			//role.equalsIgnoreCase("ADMIN")
			if(students.get(i).getrollNumber()==studentId)
				result=true;
				
		}
		return result;
	}

	public static boolean isValidCourse(List<Course> assignedCourses,String courseCode)
	{
		//check if course is valid
		boolean result=false;
		for(int i=0;i<assignedCourses.size();i++)
		{
			if(assignedCourses.get(i).getCourseCode().equalsIgnoreCase(courseCode))
				result= true;
		}
		return result;
	}

}
