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
			System.err.println(e.getMessage());
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
	public boolean verifyCredentials(String userId, String password) {
		Connection connection = DBUtils.getConnection();
		try {
			// open db connection
			PreparedStatement preparedStatement = connection.prepareStatement(SQLQueriesConstants.VERIFY_CREDENTIALS);
			preparedStatement.setString(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next())
				return false;
			// TODO : Hash + Salt + Pepper can be addded without much trouble
			return password.equals(resultSet.getString("password"));

		} catch (SQLException ex) {
			System.out.println("Message -  ");
			System.out.println("Something went wrong, please try again! " + ex.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String generatePassword(String userPassword) {
		String Salt = CryptoUtils.getRandomSatl();
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
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_ROLE);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return rs.getString("role");
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}