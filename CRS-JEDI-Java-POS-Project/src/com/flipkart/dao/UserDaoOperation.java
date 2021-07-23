package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.flipkart.bean.User;
import com.flipkart.constant.Gender;
import com.flipkart.constant.Role;
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

	public static boolean deleteUserObjectFromUserId(int userId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.DELETE_USER_FROM_USER_ID;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute);
			preparedStatement.setInt(1, userId);
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				return false;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			return true;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	public static User getUserFromUserId(int userId) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_USER_INFO_FROM_USERID;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute);
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			// userId, name, email, password, role, gender, address, country, doj
			// int userId = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String email = resultSet.getString(3);
			String password = resultSet.getString(4);
			String role = resultSet.getString(5);
			String gender = resultSet.getString(6);
			String address = resultSet.getString(7);
			String country = resultSet.getString(8);
			LocalDateTime doj = DBUtils.parseDate(resultSet.getString(9));
			User user = new User();
			user.setUserId(userId);
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(Role.valueOf(role));
			user.setGender(Gender.valueOf(gender));
			user.setAddress(address);
			user.setCountry(country);
			user.setDoj(doj);
			return user;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return null;
	}

	public static User getUserFromEmail(String email) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.GET_USER_INFO_FROM_EMAIL;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute);
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
				// @yaduraj
				// TODO : Add exception User Record not delete
			}
			// userId, name, email, password, role, gender, address, country, doj
			int userId = resultSet.getInt(1);
			String name = resultSet.getString(2);
			// String email = resultSet.getString(3);
			String password = resultSet.getString(4);
			String role = resultSet.getString(5);
			String gender = resultSet.getString(6);
			String address = resultSet.getString(7);
			String country = resultSet.getString(8);
			LocalDateTime doj = DBUtils.parseDate(resultSet.getString(9));
			User user = new User();
			user.setUserId(userId);
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			user.setRole(Role.valueOf(role));
			user.setGender(Gender.valueOf(gender));
			user.setAddress(address);
			user.setCountry(country);
			user.setDoj(doj);
			return user;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return null;
	}

	public static boolean createDBRecordAndUpdateObject(User user) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_USER_QUERY;
		try {
			user.setPassword(CryptoUtils.generateDatabasePassword(user.getPassword()));
			PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute);
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getGender().toString());
			preparedStatement.setString(3, user.getAddress());
			preparedStatement.setString(4, user.getCountry());
			preparedStatement.setString(5, user.getRole().toString());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getEmail());
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 0) {
				return false;
				// @yaduraj
				// TODO : Add exception User Record not created
			}
			User userFromDB = getUserFromEmail(user.getEmail());
			assert userFromDB != null;
			user.setUserId(userFromDB.getUserId());
			user.setDoj(userFromDB.getDoj());
			return true;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	public static UserDaoOperation getInstance() {
		if (instance == null) {
			instance = new UserDaoOperation();
		}
		return instance;
	}

	@Override
	public boolean updatePassword(String email, String newPassword) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.UPDATE_PASSWORD;
		// update user set password=? where email = ?
		try {
			PreparedStatement statement = connection.prepareStatement(queryToExecute);
			statement.setString(1, CryptoUtils.generateDatabasePassword(newPassword));
			statement.setString(2, email);
			int row = statement.executeUpdate();
			if (row == 1)
				return true;
			else
				return false;
		} catch (SQLException sqlErr) {
			System.err.printf("Error in Executing Query %s\n%s\n", queryToExecute, sqlErr.getMessage());
			sqlErr.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException closeErr) {
				System.err.printf("Error in Closing Connection %s\n%s\n", queryToExecute, closeErr.getMessage());
				closeErr.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean verifyCredentials(String email, String password) {
		User user = UserDaoOperation.getUserFromEmail(email);
		if (user == null)
			return false;
		return CryptoUtils.verifyPassword(password, user.getPassword());
	}

	@Override
	public boolean updatePassword(String userID) {
		return false;
	}

	@Override
	public Role getRole(String email) {
		User user = UserDaoOperation.getUserFromEmail(email);
		if (user == null)
			return null;
		return user.getRole();
	}
}