package com.flipkart.business;

import com.flipkart.constant.Role;

public interface UserInterface {

	boolean updatePassword(String userID, String newPassword);

	public boolean verifyCredentials(String userID, String password);

	public Role getRole(String email);

}
