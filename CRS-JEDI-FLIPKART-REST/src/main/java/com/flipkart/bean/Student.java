package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

/**
 * 
 * 
 * Student Class
 * 
 */
public class Student extends User {
	private String branchName;
	private int studentId;
	private int batch;
	private boolean isApproved;

	/**
	 * Parameterized Constructor
	 * 
	 * @param userId:     email address of the user
	 * @param name:       user full name
	 * @param role:       role among student, professor, admin
	 * @param password:   user password
	 * @param gender:     gender
	 * @param address:    address of the user
	 * @param country:    user country
	 * @param branchName: branch name
	 * @param studentId:  student id
	 * @param batch:      batch
	 * @param isApproved: check if student is approved by the admin or not
	 */
	public Student(String userId, String name, Role role, String password, Gender gender, String address,
			String country, String branchName, int studentId, int batch, boolean isApproved) {
		super(userId, name, role, password, gender, address, country);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isApproved = isApproved;
	}

	/**
	 * Default Constructor
	 */
	public Student() {

	}

	/**
	 * Method to get Branch Name of Student
	 * 
	 * @return Branch Name
	 */
	public String getBranchName() {
		return branchName;
	}

	/**
	 * Method to set Branch Name of Student
	 * 
	 * @param branchName: Branch Name
	 */
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	/**
	 * Method to get Student Id
	 * 
	 * @return Student Id
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * Method to set Student Id
	 * 
	 * @param studentId: Id Of Student
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * Method to get Batch of student
	 * 
	 * @return Batch
	 */
	public int getBatch() {
		return batch;
	}

	/**
	 * Method to set Batch of student
	 * 
	 * @param batch: Batch Number
	 */
	public void setBatch(int batch) {
		this.batch = batch;
	}

	/**
	 * Method to check approval status of student
	 * 
	 * @return Approval Status
	 */
	public boolean isApproved() {
		return isApproved;
	}

	/**
	 * Method to set approval status of student
	 *
	 * @param isApproved: If Student is approved
	 */
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return super.toString() + "\nStudent{" + "branchName='" + branchName + '\'' + ", studentId=" + studentId
				+ ", batch=" + batch + ", isApproved=" + isApproved + '}';
	}
}
