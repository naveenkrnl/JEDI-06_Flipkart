package com.flipkart.dao;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;

// import java.sql.SQLException;
// import java.util.UUID;

// import com.flipkart.constant.ModeOfPayment;
// import com.flipkart.constant.NotificationType;

/**
 * 
 * Interface for Notification Dao Operations Used for adding the notification to
 * the database
 *
 */
public interface NotificationDaoInterface {

	public boolean createPaymentDBRow(Payment payment);

	public boolean createPaymentNotification(Payment payment, Notification notification);
}