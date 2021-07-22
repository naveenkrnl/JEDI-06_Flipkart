
package com.flipkart.bean;

import java.util.Date;

public class Admin extends User {
	private Date doj;

	public Date getDoj() {
		System.out.println("Function getDoj called from Admin");
		return doj;
	}

	public void setDoj(Date doj) {
		System.out.println("Function setDoj called from Admin");
		this.doj = doj;
	}
}
