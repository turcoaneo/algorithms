package org.algorithms.test.copilot.bit;

import java.security.MessageDigest;
import java.util.Arrays;

public class KeyGenerator {
    public static String generateKeyFromSecret(String secret) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = md.digest(secret.getBytes());
        return new String(Arrays.copyOf(keyBytes, 16)); // Shorten for encryption use
    }

    public static String assembleKey(String part1, String part2) {
        char[] key = new char[part1.length()];
        for (int i = 0; i < part1.length(); i++) {
            key[i] = (char) (part1.charAt(i) ^ part2.charAt(i)); // Combine securely
        }
        return new String(key);
    }

    public static void main(String[] args) throws Exception {
        String secretPhrase = "SuperSecureSharedSecret";
        String key = generateKeyFromSecret(secretPhrase);

        System.out.println("Generated Key: " + key);

        System.out.println();


        String k1 = "SecretHalf1";
        String k2 = "SecretHalf2";

        String finalKey = assembleKey(k1, k2);
        System.out.println("Assembled Key: " + finalKey);
    }
}