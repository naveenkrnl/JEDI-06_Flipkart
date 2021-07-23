package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.flipkart.bean.Student;
import com.flipkart.application.CRSApplication;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.business.StudentOperation;
import com.flipkart.utils.DBUtils;

/**
 * Class to implement Student Dao Operations
 */
public class StudentDaoOperation implements StudentDaoInterface {

    private static StudentDaoOperation instance = null;

    private StudentDaoOperation() {

    }

    public static StudentDaoOperation getInstance() {
        if (instance == null) {
            instance = new StudentDaoOperation();
        }
        return instance;
    }

    @Override
    // TODO : Why return int
    public int addStudent(Student student) {
        Connection connection = DBUtils.getConnection();
        String QueryToExecute = SQLQueriesConstants.ADD_USER_QUERY;
        int studentId = 0;
        try {
            // open db connection
            // insert into User(name,gender,address,country,role,password,email)

            PreparedStatement preparedStatement = connection.prepareStatement(QueryToExecute);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getGender().toString());
            preparedStatement.setString(3, student.getAddress());
            preparedStatement.setString(4, student.getCountry());
            preparedStatement.setString(5, student.getRole().toString());
            preparedStatement.setString(6, student.getPassword());
            preparedStatement.setString(7, student.getEmail());
            ResultSet rs = preparedStatement.executeQuery();


            if (rs.next()==false) {
                return -1;
                // TODO : Add exception User Record not created
            }
            student.setUserId(rs.getInt("user.userId"));
			student.setDoj(DBUtils.parseDate(rs.getString("user.doj")));
            // insert into student (userId,branchName,batch,rollNumber,isApproved)

            PreparedStatement preparedStatementStudent =connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT)
            preparedStatementStudent.setInt(1, student.getUserId());
            preparedStatementStudent.setString(2, student.getBranchName());
            preparedStatementStudent.setInt(3, student.getBatch());
            preparedStatementStudent.setString(4, student.getrollNumber());
            preparedStatementStudent.setBoolean(5, false);
            ResultSet results = preparedStatementStudent.executeQuery();
            if (results.next() == false) {
                return -1;
                // TODO : Add exception Student Record not created
            }
            // TODO : logger.info user and student recode created
            // return 
            return 1;


        } catch (

        Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentId;
    }

    @Override
    public int getStudentId(String userId) {
        Connection connection = DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_ID);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getInt("studentId"); // roll no
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return 0;
    }

    @Override
    public boolean isApproved(int studentId) {
        Connection connection = DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("isApproved");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return false;
    }

}