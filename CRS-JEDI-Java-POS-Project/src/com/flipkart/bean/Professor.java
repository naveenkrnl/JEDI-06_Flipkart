/**
 * 
 */
package com.flipkart.bean;
import java.util.Date;

public class Professor extends User{
	private String department;
	private String designation;

	public Professor() {}

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
