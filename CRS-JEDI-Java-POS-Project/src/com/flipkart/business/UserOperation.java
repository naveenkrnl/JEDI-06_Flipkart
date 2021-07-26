package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exception.UserNotFoundException;

public class UserOperation implements UserInterface {
	private static UserOperation instance = null;
	final UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();

	private UserOperation() {
	}

	public static UserOperation getInstance() {
		if (instance == null) {
			instance = new UserOperation();
		}
		return instance;
	}

	@Override
	public User getUserFromEmail(String email) {
		return userDaoInterface.getUserFromEmail(email);
	}

	@Override
	public boolean updatePassword(String email, String newPassword) {
		return userDaoInterface.updatePassword(email, newPassword);
	}

	@Override
	public boolean verifyCredentials(String email, String password) throws UserNotFoundException {
		return userDaoInterface.verifyCredentials(email, password);
	}

	@Override
	public Role getRole(String email) {
		return userDaoInterface.getRole(email);
	}
}
