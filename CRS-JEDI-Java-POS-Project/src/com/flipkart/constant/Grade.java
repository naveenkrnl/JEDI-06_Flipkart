package com.flipkart.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Enumeration class for Grade
 * 
 */

// Some changes done in the Grade enum

public enum Grade {

    A, A_MINUS, B, B_MINUS, C, C_MINUS, D, D_MINUS, E, FAIL, NOT_GRADED;

    public static final Map<String, Integer> gradeStringToScore = new HashMap<String, Integer>();
    static {
        gradeStringToScore.put("A", 10);
        gradeStringToScore.put("A_MINUS", 9);
        gradeStringToScore.put("B", 8);
        gradeStringToScore.put("B_MINUS", 7);
        gradeStringToScore.put("C", 6);
        gradeStringToScore.put("C_MINUS", 5);
        gradeStringToScore.put("D", 4);
        gradeStringToScore.put("D_MINUS", 3);
        gradeStringToScore.put("E", 2);
        gradeStringToScore.put("FAIL", 0);
        gradeStringToScore.put("NOT_GRADED", 0);

    }

    public int getValue() {
        return gradeStringToScore.get(toString());
    }

    // final private int value;

    // private Grade(int value) {
    // System.out.println("Function Grade called from Grade");
    // this.value = value;
    // }

    // public int hasValue() {
    // System.out.println("Function hasValue called from Grade");
    // return this.value;
    // }

    // @Override
    // public String toString() {
    // System.out.println("Function toString called from Grade");
    // return "";
    // }

    // public static Grade stringToGrade(String input) {

    // Grade grade;
    // switch (input) {
    // case "EX":
    // grade = Grade.EX;
    // break;
    // case "A":
    // grade = Grade.A;
    // break;
    // case "B":
    // grade = Grade.B;
    // break;
    // case "C":
    // grade = Grade.C;
    // break;
    // case "D":
    // grade = Grade.D;
    // break;
    // case "E":
    // grade = Grade.E;
    // break;
    // case "F":
    // grade = Grade.F;
    // break;
    // default:
    // return null;
    // }
    // return grade;
    // }
}