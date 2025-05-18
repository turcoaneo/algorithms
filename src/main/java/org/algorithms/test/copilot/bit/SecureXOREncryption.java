package org.algorithms.test.copilot.bit;

import java.security.SecureRandom;

public class SecureXOREncryption {

    // Generate a secure random key
    public static String generateRandomKey(int length) {
        SecureRandom random = new SecureRandom();
        char[] key = new char[length];

        for (int i = 0; i < length; i++) {
            key[i] = (char) (random.nextInt(126 - 33) + 33); // Generates printable ASCII characters
        }

        return new String(key);
    }

    // XOR-based encryption/decryption
    @SuppressWarnings("DuplicatedCode")
    public static String xorEncryptDecrypt(String input, String key) {
        char[] output = new char[input.length()];

        for (int i = 0; i < input.length(); i++) {
            output[i] = (char) (input.charAt(i) ^ key.charAt(i % key.length()));
        }

        return new String(output);
    }

    public static void main(String[] args) {
        String message = "ConfidentialData";
        String key = generateRandomKey(message.length()); // Generate key matching message length

        System.out.println("Generated Key: " + key);

        String encrypted = xorEncryptDecrypt(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = xorEncryptDecrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted); // Should return original message
    }
}