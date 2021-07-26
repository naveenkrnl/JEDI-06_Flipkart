package com.flipkart.bean;

import java.util.Date;

/**
 * Professor Class
 */
public class Professor extends User {
	private String department;
	private String designation;

	/**
	 * Constructor
	 */
	public Professor() {
	}

	/**
	 * 
	 * @return: get the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * 
	 * @param designation: get the designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * 
	 * @return department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * 
	 * @param department: department for the professor
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
