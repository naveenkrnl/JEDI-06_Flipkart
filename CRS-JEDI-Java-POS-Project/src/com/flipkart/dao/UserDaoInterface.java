package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.UserNotFoundException;

/**
 * 
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {

	@SuppressWarnings("UnusedReturnValue")
	boolean deleteUserObjectFromUserId(int userId);

	User getUserFromUserId(int userId);

	User getUserFromEmail(String email);

	boolean createDBRecordAndUpdateObject(User user);

	boolean updatePassword(String email, String newPassword);

	boolean verifyCredentials(String email, String password) throws UserNotFoundException;

	Role getRole(String email);
}