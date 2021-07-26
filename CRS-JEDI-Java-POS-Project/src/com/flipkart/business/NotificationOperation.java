package com.flipkart.business;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

import java.util.UUID;

public class NotificationOperation implements NotificationInterface {

	private NotificationOperation() {
	}

	public static NotificationOperation getInstance() {
		System.out.println("Function getInstance called from NotificationOperation");
		return null;
	}

	@Override
	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
		System.out.println("Function sendNotification called from NotificationOperation");
		return 0;
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		System.out.println("Function getReferenceId called from NotificationOperation");
		return null;
	}
}
