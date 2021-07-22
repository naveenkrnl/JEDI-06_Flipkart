
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public abstract class User {

	public String name;
	public Gender gender;
	public String address;
	public String country;

	public String userId;
	public Role role;
	public String password;
	public String email;

	public User(String userId, String name, Role role, String password, Gender gender, String address, String country) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.country = country;
	}

	public Gender getGender() {
		System.out.println("Function getGender called from User");
		return gender;
	}

	public void setGender(Gender gender) {
		System.out.println("Function setGender called from User");
		this.gender = gender;
	}

	public String getAddress() {
		System.out.println("Function getAddress called from User");
		return address;
	}

	public void setAddress(String address) {
		System.out.println("Function setAddress called from User");
		this.address = address;
	}

	public String getCountry() {
		System.out.println("Function getCountry called from User");
		return country;
	}

	public void setCountry(String country) {
		System.out.println("Function setCountry called from User");
		this.country = country;
	}

	public User() {
		System.out.println("Function User called from User");
	}

	public String getUserId() {
		System.out.println("Function getUserId called from User");
		return userId;
	}

	public void setUserId(String userId) {
		System.out.println("Function setUserId called from User");
		this.userId = userId;
	}

	public String getName() {
		System.out.println("Function getName called from User");
		return name;
	}

	public void setName(String name) {
		System.out.println("Function setName called from User");
		this.name = name;
	}

	public Role getRole() {
		System.out.println("Function getRole called from User");
		return role;
	}

	public void setRole(Role role) {
		System.out.println("Function setRole called from User");
		this.role = role;
	}

	public String getPassword() {
		System.out.println("Function getPassword called from User");
		return password;
	}

	public void setPassword(String password) {
		System.out.println("Function setPassword called from User");
		this.password = password;
	}
}
