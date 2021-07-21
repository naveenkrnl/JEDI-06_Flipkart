package com.flipkart.business;

import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;


public class NotificationOperation implements NotificationInterface {

	private NotificationOperation(){}

	public static NotificationOperation getInstance() {
	}

	@Override
	public int sendNotification(NotificationType type, int studentId,ModeOfPayment modeOfPayment,double amount) {
	}

	@Override
	public UUID getReferenceId(int notificationId) {
		return null;
	}

}
