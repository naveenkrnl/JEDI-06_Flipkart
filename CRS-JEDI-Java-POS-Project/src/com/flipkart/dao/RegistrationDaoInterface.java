package com.flipkart.dao;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;

/**
 * Interface for Registration DAO Operation
 *
 */
public interface RegistrationDaoInterface {
	

	public boolean addCourse(String courseCode, int studentId) throws SQLException;

	public boolean dropCourse(String courseCode, int studentId) throws SQLException;

	public List<Course> viewCourses(int studentId) throws SQLException;

	public List<Course> viewRegisteredCourses(int studentId) throws SQLException;

	public List<GradeCard> viewGradeCard(int studentId) throws SQLException;

	public double calculateFee(int studentId) throws SQLException;

	public boolean seatAvailable(String courseCode) throws SQLException;

	public int numOfRegisteredCourses(int studentId) throws SQLException;

	public boolean isRegistered(String courseCode, int studentId) throws SQLException;

	public boolean getRegistrationStatus(int studentId) throws SQLException;

	public void setRegistrationStatus(int studentId) throws SQLException;

}