package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.Grade;

import java.util.List;

public interface ProfessorInterface {
	Professor getProfessorFromEmail(String email);

	boolean addGrade(int studentUserId, int courseId, Grade grade);

	List<RegisteredCourse> viewEnrolledStudents(int professorUserId);

	List<Course> getCourses(int professorUserId);

	Professor getProfessorById(int professorUserId);
}
