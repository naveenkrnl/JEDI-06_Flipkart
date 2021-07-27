package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;

public interface NotificationInterface {

    boolean createPaymentNotification(Payment payment, Notification notification);
}
