package com.flipkart.constant;

/**
 * 
 * Enumeration class for Grade
 * 
 */
public enum Grade {
    A(10), A_MINUS(9), B(8), B_MINUS(7), C(6), C_MINUS(5), D(4), E(3);

    final private int value;

    private Grade(int value) {
        System.out.println("Function Grade called from Grade");
        this.value = value;
    }

    public int hasValue() {
        System.out.println("Function hasValue called from Grade");
        return this.value;
    }

    @Override
    public String toString() {
        System.out.println("Function toString called from Grade");
        return "";
    }

}