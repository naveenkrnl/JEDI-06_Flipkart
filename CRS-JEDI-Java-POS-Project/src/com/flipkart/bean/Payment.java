package com.flipkart.bean;

import com.flipkart.constant.ModeOfPayment;

import java.time.LocalDateTime;

public class Payment {

    private String iD;
    private int studentId;
    private String referenceId;
    private int amount;
    private LocalDateTime dateOfPayment;
    private ModeOfPayment modeOfPayment;

    public Payment(String iD, int studentId, String referenceId, int amount, LocalDateTime dateOfPayment,
            ModeOfPayment modeOfPayment) {
        this.iD = iD;
        this.studentId = studentId;
        this.referenceId = referenceId;
        this.amount = amount;
        this.dateOfPayment = dateOfPayment;
        this.modeOfPayment = modeOfPayment;
    }

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

}
