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

	boolean createDBRecordAndUpdateObject(Professor professor);

	Professor getProfessorFromUserIdImpl(int userId);

	Professor getProfessorFromUserId(int userId);

	Professor getProfessorFromEmail(String email);

	List<Course> getCoursesByProfessorUserId(int professorUserId);

	String getProfessorNameByUserId(int userId);

	List<RegisteredCourse> getEnrolledStudents(int professorUserId);

	boolean addGradeToStudent(int studentUserId, int courseId, Grade grade);
}