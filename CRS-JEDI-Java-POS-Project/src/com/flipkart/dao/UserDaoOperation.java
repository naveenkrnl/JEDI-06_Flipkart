package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
import com.flipkart.utils.CryptoUtils;
import com.flipkart.utils.Secrets;

/**
 * 
 * Class to implement User Dao Operations
 */
public class UserDaoOperation implements UserDaoInterface {
	private static volatile UserDaoOperation instance = null;

	private UserDaoOperation() {

	}

	public static UserDaoOperation getInstance() {
		if (instance == null) {
			instance = new UserDaoOperation();
		}
		return instance;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		Connection connection = DBUtils.getConnection();
		String QueryToExecute = SQLQueriesConstants.UPDATE_PASSWORD;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryToExecute);
			// @yaduraj
			// TODO : use generateDatabasePassword here
			statement.setString(1, newPassword);
			statement.setString(2, userId);

			int row = statement.executeUpdate();

			if (row == 1)
				return true;
			else
				return false;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", QueryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", QueryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean verifyCredentials(String userId, String password) {
		Connection connection = DBUtils.getConnection();
		String QueryToExecute = SQLQueriesConstants.VERIFY_CREDENTIALS;
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(QueryToExecute);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next())
				return false;
			// @yaduraj
			// TODO : use verifyPassword here
			return password.equals(resultSet.getString("password"));

		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", QueryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", QueryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	public static String generateDatabasePassword(String userPassword) {
		String Salt = CryptoUtils.getRandomSalt();
		String Pepper = Secrets.getPepper();
		return String.format("%s$%s", Salt,
				CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + Salt + Pepper)));
	}

	public static boolean verifyPassword(String userPassword, String databasePassword) {
		String encodedPassword = databasePassword.split("\\$", 2)[1];
		String Salt = databasePassword.split("\\$", 2)[0];
		String Pepper = Secrets.getPepper();
		return encodedPassword.equals(CryptoUtils.encodeBase64(CryptoUtils.encodeBase64(userPassword + Salt + Pepper)));
	}

	@Override
	public boolean updatePassword(String userID) {
		return false;
	}

	@Override
	public String getRole(String userId) {
		Connection connection = DBUtils.getConnection();
		String QueryToExecute = SQLQueriesConstants.GET_ROLE;
		try {
			PreparedStatement statement = connection.prepareStatement(QueryToExecute);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getString("role");
			}

		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", QueryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", QueryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return null;
	}
}