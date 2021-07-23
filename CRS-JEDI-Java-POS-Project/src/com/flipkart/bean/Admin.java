
package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

public class Admin extends User {

	// private String name;
	// private Gender gender;
	// private String address;
	// private String country;
	// private int userId;
	// private Role role;
	// private String password;
	// private String email;
	// private LocalDateTime doj;

	public Admin(String name, Gender gender, String address, String country, Integer userId, Role role, String password,
			String email, LocalDateTime doj) {
		super(name, gender, address, country, userId, role, password, email, doj);
	}
}
