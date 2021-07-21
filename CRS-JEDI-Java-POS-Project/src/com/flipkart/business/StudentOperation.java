package com.flipkart.business;

import com.flipkart.constant.Gender;

public class StudentOperation implements StudentInterface {


	private StudentOperation()
	{
		
	}

	public static StudentOperation getInstance()
	{
	}
	

	@Override
	public int register(String name,String userId,String password,Gender gender,int batch,String branch,String address,String country) {
	}
	

	@Override
	public int getStudentId(String userId) {

	}
	

	@Override
	public boolean isApproved(int studentId) {

	}


}
