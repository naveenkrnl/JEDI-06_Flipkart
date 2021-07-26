package com.flipkart.business;

import com.flipkart.constant.Role;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exception.UserNotFoundException;

public class UserOperation implements UserInterface {

	private static volatile UserOperation instance = null;
	UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();

	private UserOperation() {

	}

	public static UserOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (UserOperation.class) {
				instance = new UserOperation();
			}
		}
		return instance;
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) {
		return userDaoInterface.updatePassword(userID, newPassword);
	}

	@Override
	public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
		try {
			return userDaoInterface.verifyCredentials(userID, password);
		} finally {

		}
	}

	@Override
	public String getRole(String userId) {
		return userDaoInterface.getRole(userId);
	}

	@Override
	public boolean checkExistence(String userID, String role){
		return userDaoInterface.checkExistence(userID, role);
	}

}
