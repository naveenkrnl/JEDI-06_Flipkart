
package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public class Student extends User {

	// private String name;
	// private Gender gender;
	// private String address;
	// private String country;
	// private int userId;
	// private Role role;
	// private String password;
	// private String email;
	// private LocalDateTime doj;

	private String branchName;
	private String rollNumber;
	private int batch;
	private boolean isApproved;

	public Student() {

	}

	public Student(String name, Gender gender, String address, String country, int userId, Role role, String password,
			String email, LocalDateTime doj, String branchName, String rollNumber, int batch) {
		super(name, gender, address, country, userId, role, password, email, doj);
		this.branchName = branchName;
		this.rollNumber = rollNumber;
		this.batch = batch;
		this.isApproved = false;
	}

	@Override
	public String toString() {

		return "\n********************************************\n"
				+ String.format("*********    Student Details of %s *********\n", getName()) + "Email = " + getUserId()
				+ "\nCountry = " + getCountry() + "\nGender = " + getGender().toString() + "\nAddress = " + getAddress()
				+ "\nBranchName = " + getBranchName() + "\nBatch = " + getBatch() + "\n"
				+ "********************************************";
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getrollNumber() {
		return "BE/" + Integer.toString(getUserId()) + "/" + Integer.toString(getBatch());
	}

	public void setrollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

}
