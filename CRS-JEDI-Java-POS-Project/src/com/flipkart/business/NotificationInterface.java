package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

import java.util.UUID;

public interface NotificationInterface {

    public boolean createPaymentNotification(Payment payment, Notification notification);
}
