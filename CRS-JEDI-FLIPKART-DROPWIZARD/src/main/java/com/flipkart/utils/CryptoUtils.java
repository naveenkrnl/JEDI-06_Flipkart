package com.flipkart.utils;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for Login Passwords
 */
public class CryptoUtils {
    /**
     * @return salt
     */
    public static String getRandomSalt() {
        byte[] salt = new byte[16];
        SecureRandom secure_random = new SecureRandom();
        secure_random.nextBytes(salt);
        return encodeBase64(new String(salt));
    }

    /**
     * @param str String
     * @return String
     */
    public static String encodeBase64(String str) {
        byte[] encodedBytes = Base64.getEncoder().encode(str.getBytes());
        return new String(encodedBytes);
    }

    /**
     * @param str String
     * @return String
     */
    public static String hashString(String str) {
        int hashcode = str.hashCode();
        return Integer.toString(hashcode);
    }

    /**
     * @param userPassword Password Of user
     * @return String
     */
    public static String generateDatabasePassword(String userPassword) {
        String Salt = CryptoUtils.getRandomSalt();
        String Pepper = Secrets.getPepper();
        return String.format("%s$%s", Salt,
                CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + Salt + Pepper)));
    }

    /**
     * @param userPassword User Password
     * @param databasePassword Password Stored in database
     * @return boolean
     */
    public static boolean verifyPassword(String userPassword, String databasePassword) {
        String encodedPassword = databasePassword.split("\\$", 2)[1];
        String Salt = databasePassword.split("\\$", 2)[0];
        String Pepper = Secrets.getPepper();
        // TODO : If user role Student check if approved
        return encodedPassword.equals(CryptoUtils.encodeBase64(CryptoUtils.hashString(userPassword + Salt + Pepper)));
    }

}
