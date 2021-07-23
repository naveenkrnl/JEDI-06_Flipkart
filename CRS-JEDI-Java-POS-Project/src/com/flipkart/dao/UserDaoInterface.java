package com.flipkart.dao;

import com.flipkart.constant.Role;

/**
 * 
 * Interface for User Dao Operations
 *
 */
public interface UserDaoInterface {

	public boolean verifyCredentials(String userId, String password);

	public boolean updatePassword(String userID);

	public Role getRole(String email);

	public boolean updatePassword(String userId, String newPassword);
}