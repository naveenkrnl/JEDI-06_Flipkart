package com.flipkart.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtils {
    public static String getRandomSatl() {
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

}
