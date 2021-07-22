/**
 * 
 */
package com.flipkart.bean;

import java.util.Date;

public class Professor extends User {
	private String department;
	private String designation;
	private Date doj;

	public Professor() {
	}

	public String getDesignation() {
		System.out.println("Function getDesignation called from Professor");
		return designation;
	}

	public void setDesignation(String designation) {
		System.out.println("Function setDesignation called from Professor");
		this.designation = designation;
	}

	public String getDepartment() {
		System.out.println("Function getDepartment called from Professor");
		return department;
	}

	public void setDepartment(String department) {
		System.out.println("Function setDepartment called from Professor");
		this.department = department;
	}
}
