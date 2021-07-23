package com.flipkart.business;

import com.flipkart.constant.Role;
import org.apache.log4j.Logger;

public class UserOperation implements UserInterface {
	private static Logger logger = Logger.getLogger(UserOperation.class);

	private UserOperation() {
	}

	public static UserOperation getInstance() {
		logger.info("Function getInstance called from UserOperation");
		return new UserOperation();
	}

	@Override
	public boolean updatePassword(String userID, String newPassword) {
		logger.info("Function updatePassword called from UserOperation");
		return false;
	}

	@Override
	public boolean verifyCredentials(String userID, String password) {
		logger.info("Function verifyCredentials called from UserOperation");
		return true;
	}

	@Override
	public String getRole(String userId) {
		logger.info("Function getRole called from UserOperation");

		if(userId.contains("admin"))
			return "ADMIN";
		else if(userId.contains("professor"))
			return "PROFESSOR";
		else if(userId.contains("student"))
			return "STUDENT";
		return "STUDENT";
	}

}
