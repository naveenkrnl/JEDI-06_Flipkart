package com.flipkart.business;

import com.flipkart.constant.Role;
import org.apache.log4j.Logger;

import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;

public class UserOperation implements UserInterface {
	private static UserOperation instance = null;
	UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();
	private static Logger logger = Logger.getLogger(UserOperation.class);

	private UserOperation() {
	}

	public static UserOperation getInstance() {

		return new UserOperation();

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

		if(userId.contains("admin"))
			return "ADMIN";
		else if(userId.contains("professor"))
			return "PROFESSOR";
		else if(userId.contains("student"))
			return "STUDENT";

		return userDaoInterface.getRole(userId);
	}

}
