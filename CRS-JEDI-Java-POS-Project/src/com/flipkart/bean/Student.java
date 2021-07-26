
package com.flipkart.bean;

import java.time.LocalDateTime;

import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
import com.flipkart.dao.StudentDaoInterface;
import com.flipkart.dao.StudentDaoOperation;

public class Student extends User {

	// private String name;
	// private Gender gender;
	// private String address;
	// private String country;
	// private int userId;
	// private Role role;
	// private String password;
	// private String email;
	// private LocalDateTime doj;

	private String branchName;
	private String rollNumber;
	private int batch;
	private boolean isApproved;
	StudentDaoInterface studentDaoInterface = StudentDaoOperation.getInstance();

	public Student() {
		setRole(Role.STUDENT);
		batch = -1;
		isApproved = false;
	}

	@Override
	public String toString() {
		return super.toString() + "\nStudent [batch=" + batch + ", branchName=" + branchName + ", isApproved="
				+ isApproved + ", rollNumber=" + rollNumber + "]";
	}

	public String prettyPrint() {

		return "\n********************************************\n"
				+ String.format("*********    Student Details of %s *********\n", getName()) + "Email = " + getUserId()
				+ "\nCountry = " + getCountry() + "\nGender = " + getGender().toString() + "\nAddress = " + getAddress()
				+ "\nBranchName = " + getBranchName() + "\nBatch = " + getBatch() + "\n"
				+ "********************************************";
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getrollNumber() {
		if (rollNumber == null || rollNumber.length() == 0)
			rollNumber = "BE/" + getUserId() + "/" + getBatch();
		return rollNumber;
	}

	public void setrollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getBatch() {
		return batch;
	}

	public void setBatch(int batch) {
		this.batch = batch;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public Student(String name, Gender gender, String address, String country, int userId, String password,
			String email, LocalDateTime doj, String branchName, String rollNumber, int batch) {
		super(name, gender, address, country, userId, Role.STUDENT, password, email, doj);
		this.branchName = branchName;
		this.rollNumber = rollNumber;
		this.batch = batch;
		this.isApproved = false;
		setRole(Role.STUDENT);
	}

	public Student(User user) {
		super(user.getName(), user.getGender(), user.getAddress(), user.getCountry(), user.getUserId(), Role.STUDENT,
				user.getPassword(), user.getEmail(), user.getDoj());
		Student student = studentDaoInterface.getStudentFromUserIdImpl(user.getUserId());
		setUserId(student.getUserId());
		setBranchName(student.getBranchName());
		setBatch(student.getBatch());
		setrollNumber(student.getrollNumber());
		setApproved(student.isApproved());
		setRole(Role.STUDENT);
	}

	public boolean isStudentValidForDatabase() {
		if (branchName == null || branchName.length() == 0)
			return false;
		if (batch == -1)
			return false;
		getrollNumber();
		if (getRole() != Role.STUDENT)
			setRole(Role.STUDENT);
		return getRole() == Role.STUDENT && isUserValidForDatabase();
	}
}
