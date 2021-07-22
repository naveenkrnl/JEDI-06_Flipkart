
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public class Student extends User {
	private String branchName;
	private String rollNumber;
	private int batch;
	private boolean isApproved;

	public Student(String userId, String name, Role role, String password, Gender gender, String address,
			String country, String branchName, String rollNumber, int batch, boolean isApproved) {
		super(userId, name, role, password, gender, address, country);
		this.branchName = branchName;
		this.rollNumber = rollNumber;
		this.batch = batch;
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {

		return "\n********************************************\n"
				+ String.format("*********    Student Details of %s *********\n", name) + "Email = " + userId
				+ "\nCountry = " + country + "\nGender = " + gender + "\nAddress = " + address + "\nBranchName = "
				+ branchName + "\nBatch = " + batch + "\n" + "********************************************";
		// return
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

	public String getrollNumber() {
		System.out.println("Function getrollNumber called from Student");
		return rollNumber;
	}

	public void setrollNumber(String rollNumber) {
		System.out.println("Function setrollNumber called from Student");
		this.rollNumber = rollNumber;
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
