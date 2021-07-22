package com.flipkart.DAO;

import java.util.*;
import com.flipkart.bean.Course;
import com.flipkart.bean.RegisteredCourseStudent;
import com.flipkart.bean.Student;

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