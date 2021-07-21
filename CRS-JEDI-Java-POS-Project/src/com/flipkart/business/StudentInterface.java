package com.flipkart.business;

import com.flipkart.constant.Gender;

public interface StudentInterface {
	

	public int register(String name,String userID,String password,Gender gender,int batch,String branch,String address,String country);

	public int getStudentId(String userId);

    public boolean isApproved(int studentId);
}
