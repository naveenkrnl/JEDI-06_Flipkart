package com.flipkart.application;

import java.util.HashMap;

import com.flipkart.bean.Student;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;

import org.graalvm.compiler.nodes.memory.address.AddressNode.Address;

import com.flipkart.constant.Role;

public class StudentManagerCSR {
    HashMap<String, Integer> emailIDToStudentID = new HashMap<>();
    HashMap<Integer, Student> StudentIDToStudentObject = new HashMap<>();
    int minIdNotUsedYet = 0;

    // userId, name, password, address, country, branchName
    Integer getStudentIdByemailID(String emailID) {
        if (emailIDToStudentID.containsKey(emailID)) {
            return emailIDToStudentID.get(emailID);
        }
        return -1;
    }

    Student getStudentfromemailID(String emailID) {
        Integer StudentID = getStudentIdByemailID(emailID);
        if (StudentID == -1) {
            return null;
        }
        return StudentIDToStudentObject.get(StudentID);
    }

    public boolean createStudent(String emailID, String name, String password, String address, String country,
            String branchName, Gender gender, int batch) {
        if (emailIDToStudentID.containsKey(emailID)) { // Login id already present
            return false;
        }
        emailIDToStudentID.put(emailID, minIdNotUsedYet);

        // String userId, String name, Role role, String password, Gender gender, String
        // address,String country, String branchName, int studentId, int batch, boolean
        // isApproved

        Student student = new Student(emailID, name, Role.STUDENT, password, gender, address, country, branchName,
                minIdNotUsedYet, batch, true);

        StudentIDToStudentObject.put(minIdNotUsedYet, student);
        minIdNotUsedYet++;
        return true;
    }

    public boolean updateStudent(String emailID, String name, String address, String country, String branchName) {
        Student student = getStudentfromemailID(emailID);

        if (student == null) {
            return false;
        }
        name = name.trim();
        address = address.trim();
        country = country.trim();

        if (!name.isEmpty())
            student.setName(name);
        if (!address.isEmpty())
            student.setAddress(address);
        if (!country.isEmpty())
            student.setCountry(country);

        StudentIDToStudentObject.put(getStudentIdByemailID(emailID), student);
        return true;
    }

    public boolean deleteStudent(String emailID) {
        Student student = getStudentfromemailID(emailID);

        if (student == null) {
            return false;
        }

        int currStudentID = getStudentIdByemailID(emailID);
        emailIDToStudentID.remove(emailID);
        StudentIDToStudentObject.remove(currStudentID);
        return true;
    }

    public void listStudentInfo() {
        if (StudentIDToStudentObject.isEmpty()) {
            System.out.println("Empty");
            return;
        }
        // System.out.println("Listing all elements");
        for (Student Student : StudentIDToStudentObject.values()) {
            System.out.println(Student.toString());
        }
        System.out.println();
    }

    // public boolean printStudentFromId(int id) {
    // Student Student = getStudentById(id);
    // if (Student == null) {
    // System.out.println("Student not found");
    // return true;
    // }
    // System.out.println(Student.toString());
    // return false;
    // }
}
