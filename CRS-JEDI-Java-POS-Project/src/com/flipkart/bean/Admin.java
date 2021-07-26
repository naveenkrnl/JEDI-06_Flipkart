
package com.flipkart.bean;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

import java.time.LocalDateTime;

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
	public Admin() {
		setRole(Role.ADMIN);
	}

	public Admin(String name, Gender gender, String address, String country, Integer userId, String password,
			String email, LocalDateTime doj) {
		super(name, gender, address, country, userId, Role.ADMIN, password, email, doj);
		setRole(Role.ADMIN);
	}

	public Admin(User user) {
		super(user.getName(), user.getGender(), user.getAddress(), user.getCountry(), user.getUserId(), Role.ADMIN,
				user.getPassword(), user.getEmail(), user.getDoj());
		setRole(Role.ADMIN);
	}

	public boolean isAdminValidForDatabase() {
		if (getRole() != Role.ADMIN)
			setRole(Role.ADMIN);
		return getRole() == Role.ADMIN && isUserValidForDatabase();
	}

	@Override
	public String toString() {
		return super.toString() + "\nAdmin []";
	}
}
