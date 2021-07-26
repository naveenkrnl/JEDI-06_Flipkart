package com.flipkart.business;

import java.sql.SQLException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

/**
 * 
 * 
 * Implementation of notification operations.
 */
public class NotificationOperation implements NotificationInterface {

	private static volatile NotificationOperation instance = null;
	NotificationDaoInterface notificationDaoInterface = NotificationDaoOperation.getInstance();
	private static Logger logger = Logger.getLogger(NotificationOperation.class);

	private NotificationOperation() {

	}

	/**
	 * Get an instance of the class.
	 * 
	 * @return Instance of the class
	 */
	public static NotificationOperation getInstance() {
		if (instance == null) {
			// This is a synchronized block, when multiple threads will access this instance
			synchronized (NotificationOperation.class) {
				instance = new NotificationOperation();
			}
		}
		return instance;
	}

	/**
	 * Send a notification to the specified student.
	 *
	 * @param type:          Type of the notification to be sent
	 * @param studentId:     Student to be notified
	 * @param modeOfPayment: Payment mode used
	 * @param amount: Amount payable
	 * @param cardNumber: Card number
	 * @param cvv: CVV of the card
	 * @return Notification id for the record added in the database
	 */
	@Override
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv) {
		int notificationId = 0;
		try {

			notificationId = notificationDaoInterface.sendNotification(type, studentId, modeOfPayment, amount, cardNumber, cvv);

		} catch (SQLException ex) {
			logger.error("Error occurred " + ex.getMessage());
		}
		return notificationId;
	}

	/**
	 * Obtain the UUID of a transaction.
	 *
	 * @param notificationId: Notification id added in the database
	 * @return Transaction id of the payment
	 */
	@Override
	public UUID getReferenceId(int notificationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
