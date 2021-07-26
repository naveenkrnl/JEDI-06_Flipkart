package com.flipkart.business;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;

public interface UserInterface {

	boolean updatePassword(String userID, String newPassword);

	boolean verifyCredentials(String email, String password) throws UserNotFoundException;

	Role getRole(String email);

	User getUserFromEmail(String email);

}
