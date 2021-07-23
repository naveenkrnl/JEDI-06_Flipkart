
package com.flipkart.bean;

import java.time.LocalDateTime;

import javax.print.attribute.standard.DateTimeAtCreation;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public class User {

	private String name;
	private Gender gender;
	private String address;
	private String country;
	private int userId;
	private Role role;
	private String password;
	private String email;
	private LocalDateTime doj;

	public User() {
		name = null;
		gender = null;
		address = null;
		country = null;
		userId = -1;
		role = null;
		password = null;
		email = null;
		doj = null;

	}

	public User(String name, Gender gender, String address, String country, Integer userId, Role role, String password,
			String email, LocalDateTime doj) {
		this.name = name;
		this.gender = gender;
		this.address = address;
		this.country = country;
		if (userId != null)
			this.userId = userId;
		this.role = role;
		this.password = password;
		this.email = email;
		this.doj = doj;
	}

	public LocalDateTime getDoj() {
		return doj;
	}

	public void setDoj(LocalDateTime doj) {
		this.doj = doj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [address=" + address + ", country=" + country + ", doj=" + doj + ", email=" + email + ", gender="
				+ gender + ", name=" + name + ", password=" + password + ", role=" + role + ", userId=" + userId + "]";
	}

	public boolean isUserValidForDatabase() {
		if (name == null || name.trim().length() == 0)
			return false;
		if (address == null || address.trim().length() == 0)
			return false;
		if (country == null || country.trim().length() == 0)
			return false;
		if (password == null || password.trim().length() == 0)
			return false;
		if (email == null || email.trim().length() == 0)
			return false;
		if (gender == null)
			return false;
		if (role == null)
			return false;
		return true;
	}

}
