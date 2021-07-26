package com.flipkart.business;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

import java.util.UUID;

public interface NotificationInterface {

	public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount);

	public UUID getReferenceId(int notificationId);
}
