package com.flipkart.utils;

public class Secrets {
    public static final String ROOT_PASSWORD = "root";
    public static String PEPPER = CryptoUtils.encodeBase64("IFcGY9rzFlFT1Spj");

    public static String getPepper() {
        return PEPPER;
    }
}
