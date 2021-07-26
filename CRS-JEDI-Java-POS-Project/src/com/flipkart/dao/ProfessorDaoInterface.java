package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.Grade;

import java.util.List;

// import java.util.*;
// import com.flipkart.bean.Course;
// import com.flipkart.bean.RegisteredCourse;

/**
 * 
 * Interface for Admin Dao Operations
 * 
 */
public interface ProfessorDaoInterface {

	public boolean createDBRecordAndUpdateObject(Professor professor);

	public Professor getProfessorFromUserIdImpl(int userId);

	public Professor getProfessorFromUserId(int userId);

	public Professor getProfessorFromEmail(String email);

	public List<Course> getCoursesByProfessorUserId(int professorUserId);

	public String getProfessorNameByUserId(int userId);

	public List<RegisteredCourse> getEnrolledStudents(int professorUserId);

	public boolean addGradeToStudent(int studentUserId, int courseId, Grade grade);
}