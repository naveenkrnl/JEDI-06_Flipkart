package com.flipkart.bean;

import com.flipkart.constant.ModeOfPayment;

import java.time.LocalDateTime;

public class Payment {

    private int id;
    private int studentUserId;
    private String referenceId;
    private double amount;
    private LocalDateTime dateOfPayment;
    private ModeOfPayment modeOfPayment;
    private String cardNumber;
    private String cvv;
    private String expiry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentUserId() {
        return studentUserId;
    }

    public void setStudentUserId(int studentUserId) {
        this.studentUserId = studentUserId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(LocalDateTime dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    public ModeOfPayment getModeOfPayment() {
        return modeOfPayment;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "Payment [amount=" + amount + ", dateOfPayment=" + dateOfPayment + ", id=" + id + ", modeOfPayment="
                + modeOfPayment + ", referenceId=" + referenceId + ", studentUserId=" + studentUserId + "]";
    }
}
