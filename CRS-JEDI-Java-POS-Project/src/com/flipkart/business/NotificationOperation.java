package com.flipkart.business;

import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import org.apache.log4j.Logger;

public class NotificationOperation implements NotificationInterface {
	private static Logger logger = Logger.getLogger(NotificationOperation.class);

	private NotificationOperation() {
	}

	public static NotificationOperation getInstance() {
		logger.info("Function getInstance called from NotificationOperation");
		return null;
	}

	@Override
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
		logger.info("Function sendNotification called from NotificationOperation");
		return 0;
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		logger.info("Function getReferenceId called from NotificationOperation");
		return null;
	}
}
