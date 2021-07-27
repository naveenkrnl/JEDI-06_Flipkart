package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import org.apache.log4j.Logger;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.UUID;

// import com.flipkart.constant.ModeOfPayment;
// import com.flipkart.constant.NotificationType;
// import com.flipkart.constant.SQLQueriesConstants;
// import com.flipkart.business.NotificationOperation;
// import com.flipkart.utils.DBUtils;

/**
 * 
 * Class to implement Notification Dao Operations Used for adding the
 * notification to the database
 *
 */
public class NotificationDaoOperation implements NotificationDaoInterface {
	private static NotificationDaoOperation instance = null;
	static Logger logger = Logger.getLogger(NotificationDaoOperation.class.getName());

	private NotificationDaoOperation() {

	}

	public static NotificationDaoOperation getInstance() {
		if (instance == null) {
			instance = new NotificationDaoOperation();
		}
		return instance;
	}

	public boolean createPaymentDBRow(Payment payment) {
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_PAYMENT_ROW;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute, new String[] { "id" })) {
			preparedStatement.setInt(1, payment.getStudentUserId());
			preparedStatement.setString(2, payment.getReferenceId());
			preparedStatement.setDouble(3, payment.getAmount());
			preparedStatement.setString(4, payment.getModeOfPayment().toString());
			preparedStatement.setString(5, payment.getCardNumber());
			preparedStatement.setString(6, payment.getCvv());
			preparedStatement.setString(7, payment.getExpiry());
			int rowsAffected = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (rowsAffected == 0 || !resultSet.next()) {
				return false;
			}
			payment.setId(resultSet.getInt(1));
			return true;
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
		return false;
	}

	public boolean createPaymentNotification(Payment payment, Notification notification) {
		if (!createPaymentDBRow(payment))
			return false;
		Connection connection = DBUtils.getConnection();
		String queryToExecute = SQLQueriesConstants.ADD_NOTIFICATION_ROW;
		try (PreparedStatement preparedStatement = connection.prepareStatement(queryToExecute, new String[] { "id" })) {
			preparedStatement.setInt(1, payment.getId());
			preparedStatement.setString(2, notification.getMessage());
			int rowsAffected = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (rowsAffected == 0 || !resultSet.next()) {
				return false;
			}
			notification.setId(resultSet.getInt(1));
			notification.setPaymentId(payment.getId());
			return true;
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
		return false;
	}
}