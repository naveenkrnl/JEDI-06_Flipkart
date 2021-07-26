package com.flipkart.business;

import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationInterface {

	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount);

	public UUID getReferenceId(int notificationId);
}
