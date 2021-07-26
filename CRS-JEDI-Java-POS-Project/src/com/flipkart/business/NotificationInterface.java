package com.flipkart.business;

import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

/**
 * 
 * 
 * Notification logic for sending notifications for multiple events such as 1.
 * Student Registration 2. Registration Approval 3. Fee payment
 */
public interface NotificationInterface {

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
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount, String cardNumber, String cvv);

	/**
	 * Obtain the UUID of a transaction.
	 * 
	 * @param notificationId: Notification id added in the database
	 * @return Transaction id of the payment
	 */
	public UUID getReferenceId(int notificationId);
}
