package com.flipkart.bean;

import com.flipkart.constant.NotificationType;

public class Notification {
	private final int notificationId;
	private int rollNumber;
	private NotificationType type;
	private String referenceId;

	public Notification(int notificationId, int rollNumber, NotificationType type, String referenceId) {
		this.notificationId = notificationId;
		this.rollNumber = rollNumber;
		this.type = type;
		this.referenceId = referenceId;
	}

	public int getNotificationId() {
		return notificationId;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
}
