package com.flipkart.bean;


/**
    Payment Bean Class
 *
 */
public class Payment {

    private String referenceId;
    private String amount;
    private String dateOfPayment;
    private String modeOfPayment;
    private int studentId;

    /**
     * Method to get reference Id
     *
     * @return referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Method set Reference Id
     *
     * @param referenceId refernence id of transaction
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * Method to get amount
     *
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Method to set amount
     *
     * @param amount Amount of payment
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * Method to get date of payment
     *
     * @return dateofpayment
     */
    public String getDateOfPayment() {
        return dateOfPayment;
    }

    /**
     * Method to set date of payment
     *
     * @param dateOfPayment Date of payment
     */
    public void setDateOfPayment(String dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    /**
     * Method to get mode of payment
     *
     * @return modeofPayment
     */
    public String getModeOfPayment() {
        return modeOfPayment;
    }

    /**
     * Method to set mode of payment
     *
     * @param modeOfPayment mode of payment
     */
    public void setModeOfPayment(String modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    /**
     * Method to get student id
     *
     * @return studentId
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set student Id

     * @param studentId Student Id of student
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    }
