package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentInterface {

	boolean register(Student student);

	Student getStudentFromStudentUserId(int studentUserId);

	Student getStudentFromEmail(String email);
}
