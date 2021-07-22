package com.flipkart.dao;

import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;

/**
 * 
 * Interface for Admin Dao Operations
 * 
 */
public interface ProfessorDaoInterface {

	public List<Course> getCoursesByProfessor(String userId);

	public List<RegisteredCourseStudent> getEnrolledStudents(String profId);

	public Boolean addGrade(int studentId,String courseCode,String grade);

	public String getProfessorById(String profId);
}