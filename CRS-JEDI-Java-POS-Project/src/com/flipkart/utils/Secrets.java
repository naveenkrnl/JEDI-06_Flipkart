package com.flipkart.utils;

public class Secrets {
    private Secrets() {

    }

    public static final String PEPPER = CryptoUtils.encodeBase64("IFcGY9rzFlFT1Spj");

    public static String getPepper() {
        return PEPPER;
    }
}
