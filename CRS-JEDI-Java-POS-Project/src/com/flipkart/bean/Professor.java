/**
 * 
 */
package com.flipkart.bean;

import java.time.LocalDateTime;

public class Professor extends User {

	// private String name;
	// private Gender gender;
	// private String address;
	// private String country;
	// private int userId;
	// private Role role;
	// private String password;
	// private String email;
	// private LocalDateTime doj;

	private String department;
	private String designation;

	public Professor() {

	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
