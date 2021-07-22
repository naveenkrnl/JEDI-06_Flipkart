
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public class Student extends User {
	private String branchName;
	private int studentId;
	private int batch;
	private boolean isApproved;

	public Student(String userId, String name, Role role, String password, Gender gender, String address,
			String country, String branchName, int studentId, int batch, boolean isApproved) {
		super(userId, name, role, password, gender, address, country);
		this.branchName = branchName;
		this.studentId = studentId;
		this.batch = batch;
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "Student [name = " + name + "\ncountry = " + country + "\ngender = " + gender + "\naddress = " + address
				+ "\nbranchName = " + branchName + "\nbatch = " + batch + "]";
	}

	public Student() {

	}

	public String getBranchName() {
		System.out.println("Function getBranchName called from Student");
		return branchName;
	}

	public void setBranchName(String branchName) {
		System.out.println("Function setBranchName called from Student");
		this.branchName = branchName;
	}

	public int getStudentId() {
		System.out.println("Function getStudentId called from Student");
		return studentId;
	}

	public void setStudentId(int studentId) {
		System.out.println("Function setStudentId called from Student");
		this.studentId = studentId;
	}

	public int getBatch() {
		System.out.println("Function getBatch called from Student");
		return batch;
	}

	public void setBatch(int batch) {
		System.out.println("Function setBatch called from Student");
		this.batch = batch;
	}

	public boolean isApproved() {
		System.out.println("Function isApproved called from Student");
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		System.out.println("Function setApproved called from Student");
		this.isApproved = isApproved;
	}

}
