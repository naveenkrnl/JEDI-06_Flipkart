package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {

	boolean updatePassword(String userID, String newPassword);

	public boolean verifyCredentials(String email, String password) throws UserNotFoundException;

	public Role getRole(String email);

	public User getUserFromEmail(String email);

}
