package com.flipkart.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtils {
    public static String getRandomSalt() {
        byte[] salt = new byte[16];
        SecureRandom secure_random = new SecureRandom();
        secure_random.nextBytes(salt);
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
        String Salt = CryptoUtils.getRandomSalt();
        String Pepper = Secrets.getPepper();
        return String.format("%s$%s", Salt,
                CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + Salt + Pepper)));
    }

    public static boolean verifyPassword(String userPassword, String databasePassword) {
        String encodedPassword = databasePassword.split("\\$", 2)[1];
        String Salt = databasePassword.split("\\$", 2)[0];
        String Pepper = Secrets.getPepper();
        // TODO : If user role Student check if approved
        return encodedPassword.equals(CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + Salt + Pepper)));
    }

}
