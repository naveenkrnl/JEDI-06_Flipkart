package com.flipkart.business;

import com.flipkart.constant.Role;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;

public class UserOperation implements UserInterface {
	private static UserOperation instance = null;
	UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();

	private UserOperation() {
	}

	public static UserOperation getInstance() {
		if (instance == null) {
			instance = new UserOperation();
		}
		return instance;
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) {
		return userDaoInterface.updatePassword(userID, newPassword);
	}

	@Override
	public boolean verifyCredentials(String userID, String password) {
		return userDaoInterface.verifyCredentials(userID, password);
	}

	@Override
	public String getRole(String userId) {
		return userDaoInterface.getRole(userId);
	}

}
