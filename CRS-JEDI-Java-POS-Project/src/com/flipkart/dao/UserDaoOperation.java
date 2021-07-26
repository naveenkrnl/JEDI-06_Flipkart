package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;

public class UserDaoOperation implements UserDaoInterface {
	private static volatile UserDaoOperation instance = null;
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);

	private UserDaoOperation() {

	}

	public static UserDaoOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (UserDaoOperation.class) {
				instance = new UserDaoOperation();
			}
		}
		return instance;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.UPDATE_PASSWORD);

			statement.setString(1, newPassword);
			statement.setString(2, userId);

			int row = statement.executeUpdate();
			if (row == 1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
		Connection connection = DBUtils.getConnection();
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next())
				throw new UserNotFoundException(userId);
			else if (password.equals(resultSet.getString("password"))) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException ex) {
			logger.info("Something went wrong, please try again! " + ex.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean updatePassword(String userID) {
		return false;
	}

	@Override
	public String getRole(String userId) {
		Connection connection = DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ROLE);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getString("role");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Method to check if a user exists
	 *
	 * @param userID
	 * @return boolean indicating if user exists in the database
	 */
	@Override
	public boolean checkExistence(String userID, String role){
		Connection connection = DBUtils.getConnection();
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.CHECK_EXISTENCE);
			preparedStatement.setString(1, userID);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next())
				return false;
			else if (role.equals(resultSet.getString("role"))) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			logger.info("Something went wrong, please try again! " + ex.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
