package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.constant.Grade;

import java.util.List;

public interface ProfessorInterface {
	public Professor getProfessorFromEmail(String email);

	public boolean addGrade(int studentUserId, int courseId, Grade grade);

	public List<RegisteredCourse> viewEnrolledStudents(int professorUserId);

	public List<Course> getCourses(int professorUserId);

	public Professor getProfessorById(int professorUserId);
}
