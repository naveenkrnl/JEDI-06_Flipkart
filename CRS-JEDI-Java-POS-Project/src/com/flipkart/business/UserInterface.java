package com.flipkart.business;


public interface UserInterface {
	

	boolean updatePassword(String userID, String newPassword);

	public boolean verifyCredentials(String userID,String password);

    public String getRole(String userId);
   
 
}
