package com.flipkart.business;

import com.flipkart.constant.Role;

public class UserOperation implements UserInterface {

	private UserOperation() {
	}

	public static UserOperation getInstance() {
		System.out.println("Function getInstance called from UserOperation");
		return null;
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) {
		System.out.println("Function updatePassword called from UserOperation");
		return false;
	}

	@Override
	public boolean verifyCredentials(String userID, String password) {
		System.out.println("Function verifyCredentials called from UserOperation");
		return true;
	}

	@Override
	public String getRole(String userId) {
		System.out.println("Function getRole called from UserOperation");
		return "STUDENT";
	}

}
