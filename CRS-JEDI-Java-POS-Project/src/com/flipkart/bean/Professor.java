/**
 * 
 */
package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;

public class Professor extends User {

	// private String name;
	// private Gender gender;
	// private String address;
	// private String country;
	// private int userId;
	// private Role role;
	// private String password;
	// private String email;
	// private LocalDateTime doj;
	private String department;
	private String designation;

	public Professor(String name, Gender gender, String address, String country, int userId, String password,
			String email, LocalDateTime doj, String department, String designation) {
		super(name, gender, address, country, userId, Role.PROFESSOR, password, email, doj);
		this.department = department;
		this.designation = designation;
		setRole(Role.PROFESSOR);
	}

	public Professor() {
		setRole(Role.PROFESSOR);

	}

	public Professor(User user) {
		super(user.getName(), user.getGender(), user.getAddress(), user.getCountry(), user.getUserId(), Role.PROFESSOR,
				user.getPassword(), user.getEmail(), user.getDoj());
		Professor professor = ProfessorDaoOperation.getInstance().getProfessorFromUserIdImpl(user.getUserId());
		setUserId(professor.getUserId());
		setDesignation(professor.getDesignation());
		setDepartment(professor.getDepartment());
		setRole(Role.PROFESSOR);
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return super.toString() + "\nProfessor [department=" + department + ", designation=" + designation + "]";
	}

	public boolean isAdminValidForDatabase() {
		if (designation == null || designation.length() == 0)
			return false;
		if (department == null || department.length() == 0)
			return false;
		if (getRole() != Role.PROFESSOR)
			setRole(Role.PROFESSOR);
		return getRole() == Role.PROFESSOR && isUserValidForDatabase();
	}
}
