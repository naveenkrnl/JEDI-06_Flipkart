package com.flipkart.dao;

import com.flipkart.bean.User;
import com.flipkart.constant.Role;

/**
 * 
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {

	public boolean deleteUserObjectFromUserId(int userId);

	public User getUserFromUserId(int userId);

	public User getUserFromEmail(String email);

	public boolean createDBRecordAndUpdateObject(User user);

	public boolean updatePassword(String email, String newPassword);

	public boolean verifyCredentials(String email, String password);

	public Role getRole(String email);
}