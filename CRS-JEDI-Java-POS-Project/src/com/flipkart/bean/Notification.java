package com.flipkart.bean;

import com.flipkart.constant.NotificationType;

public class Notification {
	private int notificationId;
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
		System.out.println("Function getNotificationId called from Notification");
		return notificationId;
	}

	public int getrollNumber() {
		System.out.println("Function getrollNumber called from Notification");
		return rollNumber;
	}

	public void setrollNumber(int rollNumber) {
		System.out.println("Function setrollNumber called from Notification");
		this.rollNumber = rollNumber;
	}

	public NotificationType getType() {
		System.out.println("Function getType called from Notification");
		return type;
	}

	public void setType(NotificationType type) {
		System.out.println("Function setType called from Notification");
		this.type = type;
	}

	public String getReferenceId() {
		System.out.println("Function getReferenceId called from Notification");
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		System.out.println("Function setReferenceId called from Notification");
		this.referenceId = referenceId;
	}
}
