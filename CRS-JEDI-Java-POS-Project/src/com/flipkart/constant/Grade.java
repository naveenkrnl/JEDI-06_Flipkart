package com.flipkart.constant;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Enumeration class for Grade
 *
 */
public enum Grade {
    A(10), B(9), C(8), D(7), E(6), F(5), NA(0), EX(0);

    final private int value;

    /**
     * Parameterized Constructor
     *
     * @param value
     */
    private Grade(int value) {
        this.value = value;
    }

    /**
     * Method to get Grade Value
     *
     * @return Grade Value
     */
    public int hasValue() {
        return this.value;
    }

    /**
     * Method to convert Grade enum to String
     *
     * @return Grade in String
     */
    @Override
    public String toString() {

        final String name = name();

        if (name.contains("PLUS"))
            return name.charAt(0) + "+";
        else if (name.contains("MINUS"))
            return name.charAt(0) + "-";
        else
            return name;
    }

}