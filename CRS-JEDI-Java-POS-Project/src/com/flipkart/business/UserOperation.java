package com.flipkart.business;


public class UserOperation implements UserInterface {
	

	private UserOperation()
	{
	}

	public static UserOperation getInstance()
	{
	}

	@Override
	public boolean updatePassword(String userID,String newPassword) {
	}

	@Override
	public boolean verifyCredentials(String userID, String password) {
	}
	

	@Override
	public String getRole(String userId) {

	}


	

}
