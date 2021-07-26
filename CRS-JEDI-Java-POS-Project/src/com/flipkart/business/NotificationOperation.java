package com.flipkart.business;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

import java.util.UUID;

public class NotificationOperation implements NotificationInterface {

	private NotificationOperation() {
	}

	public static NotificationOperation getInstance() {
		return null;
	}

	@Override
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
		return 0;
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		return null;
	}
}
