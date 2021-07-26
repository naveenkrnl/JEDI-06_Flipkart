package com.flipkart.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtils {
    private CryptoUtils() {

    }

    public static String getRandomSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return encodeBase64(new String(salt));
    }

    public static String encodeBase64(String str) {
        byte[] encodedBytes = Base64.getEncoder().encode(str.getBytes());
        return new String(encodedBytes);
    }

    public static String hashString(String str) {
        int hashcode = str.hashCode();
        return Integer.toString(hashcode);
    }

    public static String generateDatabasePassword(String userPassword) {
        String salt = CryptoUtils.getRandomSalt();
        String pepper = Secrets.getPepper();
        return String.format("%s$%s", Salt,
                CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + salt + pepper)));
    }

    public static boolean verifyPassword(String userPassword, String databasePassword) {
        String encodedPassword = databasePassword.split("\\$", 2)[1];
        String salt = databasePassword.split("\\$", 2)[0];
        String pepper = Secrets.getPepper();
        return encodedPassword.equals(CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + salt + pepper)));
    }

}
