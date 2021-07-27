package com.flipkart.business;

import com.flipkart.bean.Notification;
import com.flipkart.bean.Payment;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

public class NotificationOperation implements NotificationInterface {
    static NotificationOperation instance;
    NotificationDaoInterface notificationDaoInterface = NotificationDaoOperation.getInstance();

    private NotificationOperation() {
    }

    public static NotificationOperation getInstance() {
        if (instance == null) {
            instance = new NotificationOperation();
        }
        return instance;
    }

    @Override
    public boolean createPaymentNotification(Payment payment, Notification notification) {
        return notificationDaoInterface.createPaymentNotification(payment, notification);
    }
}
