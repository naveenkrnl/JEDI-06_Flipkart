package com.flipkart.constant;

/**
 * 
 * Enumeration class for Grade
 * 
 */

//Some changes done in the Grade enum

public enum Grade {

    EX(10), A(9), B(8), C(7), D(6), E(5), F(4);

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

    public static Grade stringToGrade(String input){

        Grade grade;
        switch(input){
            case "EX":
                grade = Grade.EX;
                break;
            case "A":
                grade = Grade.A;
                break;
            case "B":
                grade = Grade.B;
                break;
            case "C":
                grade = Grade.C;
                break;
            case "D":
                grade = Grade.D;
                break;
            case "E":
                grade = Grade.E;
                break;
            case "F":
                grade = Grade.F;
                break;
            default:
                return null;
        }
        return grade;
    }
}