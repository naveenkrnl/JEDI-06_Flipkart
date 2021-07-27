package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class to implement Student Dao Operations
 */
public class StudentDaoOperation implements StudentDaoInterface {

    private static StudentDaoOperation instance = null;
    final UserDaoInterface userDaoInterface = UserDaoOperation.getInstance();
    static Logger logger = Logger.getLogger(StudentDaoOperation.class.getName());

    private StudentDaoOperation() {

    }

    public static StudentDaoOperation getInstance() {
        if (instance == null) {
            instance = new StudentDaoOperation();
        }
        return instance;
    }

    @Override
    public boolean createDBRecordAndUpdateObject(Student student) {
        if (!userDaoInterface.createDBRecordAndUpdateObject(student))
            return false;
        Connection connection = DBUtils.getConnection();
        String queryToExecute = SQLQueriesConstants.ADD_STUDENT;
        try (PreparedStatement preparedStatementStudent = connection.prepareStatement(queryToExecute)) {
            preparedStatementStudent.setInt(1, student.getUserId());
            preparedStatementStudent.setString(2, student.getBranchName());
            preparedStatementStudent.setInt(3, student.getBatch());
            preparedStatementStudent.setString(4, student.getrollNumber());
            preparedStatementStudent.setBoolean(5, false);
            int rowsAffected = preparedStatementStudent.executeUpdate();
            if (rowsAffected == 0) {
                userDaoInterface.deleteUserObjectFromUserId(student.getUserId());
                return false;
            }
            return true;

        } catch (SQLException sqlErr) {
            userDaoInterface.deleteUserObjectFromUserId(student.getUserId());
            logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));
        } finally {
            try {
                connection.close();
            } catch (SQLException closeErr) {
                logger.error(
                        String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));
            }
        }
        return false;
    }

    @Override
    public Student getStudentFromUserIdImpl(int userId) {
        Connection connection = DBUtils.getConnection();
        String queryToExecute = SQLQueriesConstants.GET_STUDENT_FROM_USERID;
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            // userId,branchName,batch,rollNumber,isApproved
            Student student = new Student();
            student.setUserId(resultSet.getInt(1));
            student.setBranchName(resultSet.getString(2));
            student.setBatch(resultSet.getInt(3));
            student.setrollNumber(resultSet.getString(4));
            student.setApproved(resultSet.getBoolean(5));
            return student;
        } catch (SQLException sqlErr) {
            logger.error(String.format("Error in Executing Query %s%n%s%n", queryToExecute, sqlErr.getMessage()));
        } finally {
            try {
                connection.close();
            } catch (SQLException closeErr) {
                logger.error(
                        String.format("Error in Closing Connection %s%n%s%n", queryToExecute, closeErr.getMessage()));
            }
        }
        return null;

    }

    @Override
    public Student getStudentFromUserId(int userId) {
        User user = userDaoInterface.getUserFromUserId(userId);
        if (user == null)
            return null;
        return new Student(user);

    }

    @Override
    public Student getStudentFromEmail(String email) {
        User user = userDaoInterface.getUserFromEmail(email);
        if (user == null)
            return null;
        return new Student(user);
    }

    @Override
    public int getStudentUserId(String email) {
        User user = userDaoInterface.getUserFromEmail(email);
        if (user == null)
            return -1;
        return user.getUserId();
    }

    @Override
    public boolean isApproved(int userId) {
        Student student = getStudentFromUserId(userId);
        if (student == null)
            return false;
        return student.isApproved();
    }
}