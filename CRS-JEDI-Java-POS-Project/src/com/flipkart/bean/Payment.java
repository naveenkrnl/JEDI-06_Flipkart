package com.flipkart.bean;

import com.flipkart.constant.ModeOfPayment;

import java.util.DateTime;

public class Payment {
    private String iD;
    private int studentId;
    private String referenceId;
    private int amount;
    private DateTime dateOfPayment;
    private ModeOfPayment modeOfPayment;
}
