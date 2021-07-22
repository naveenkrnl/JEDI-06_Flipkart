
package com.flipkart.bean;

import java.time.LocalDateTime;

public class Admin extends User {
	private LocalDateTime doj;

	public LocalDateTime getDoj() {
		System.out.println("Function getDoj called from Admin");
		return doj;
	}

	public void setDoj(LocalDateTime doj) {
		System.out.println("Function setDoj called from Admin");
		this.doj = doj;
	}
}
