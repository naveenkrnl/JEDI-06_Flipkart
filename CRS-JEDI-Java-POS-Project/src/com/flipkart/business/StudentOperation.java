package com.flipkart.business;

import com.flipkart.constant.Gender;

public class StudentOperation implements StudentInterface {

	private StudentOperation() {

	}

	public static StudentOperation getInstance() {
		System.out.println("Function getInstance called from StudentOperation");
		return null;
	}

	@Override
	public int register(String name, String userId, String password, Gender gender, int batch, String branch,
			String address, String country) {
		System.out.println("Function register called from StudentOperation");
		return 0;
	}

	@Override
	public int getStudentId(String userId) {
		System.out.println("Function getStudentId called from StudentOperation");
		return 0;

	}

	@Override
	public boolean isApproved(int studentId) {
		System.out.println("Function isApproved called from StudentOperation");
		return false;

	}

}
