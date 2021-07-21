
package com.flipkart.bean;

import java.util.Date;

public class Admin extends User {
	private Date dateOfJoining;

	public Date getDateOfJoining() {
		System.out.println("Function getDateOfJoining called from Admin");
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		System.out.println("Function setDateOfJoining called from Admin");
		this.dateOfJoining = dateOfJoining;
	}
}
