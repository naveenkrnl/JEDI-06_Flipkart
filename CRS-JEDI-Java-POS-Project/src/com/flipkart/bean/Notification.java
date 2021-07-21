package com.flipkart.bean;

import com.flipkart.constant.NotificationType;

public class Notification {
	private int notificationId;
	private int studentId;
	private NotificationType type;
	private String referenceId;

	public Notification(int notificationId, int studentId, NotificationType type, String referenceId) {
		this.notificationId = notificationId;
		this.studentId = studentId;
		this.type = type;
		this.referenceId = referenceId;
	}

	public int getNotificationId() {
		System.out.println("Function getNotificationId called from Notification");
		return notificationId;
	}

	public int getStudentId() {
		System.out.println("Function getStudentId called from Notification");
		return studentId;
	}

	public void setStudentId(int studentId) {
		System.out.println("Function setStudentId called from Notification");
		this.studentId = studentId;
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
