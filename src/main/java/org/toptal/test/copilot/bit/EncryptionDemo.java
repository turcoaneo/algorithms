package org.toptal.test.copilot.bit;

public class EncryptionDemo {
    public static String xorEncryptDecrypt(String input, char key) {
        char[] output = new char[input.length()];
        for (int i = 0; i < input.length(); i++) {
            output[i] = (char) (input.charAt(i) ^ key);
        }
        return new String(output);
    }
    public static String xorEncryptDecrypt(String input, String key) {
        char[] output = new char[input.length()];

        for (int i = 0; i < input.length(); i++) {
            output[i] = (char) (input.charAt(i) ^ key.charAt(i % key.length()));
        }

        return new String(output);
    }

    public static void main(String[] args) {
        String original = "Hello";
        char key = 'K'; // Secret key

        String encrypted = xorEncryptDecrypt(original, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = xorEncryptDecrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted); // Should return "Hello"

        System.out.println();


        original = "Hello World!";
        String keyString = "SecretKey"; // Multi-character key

        encrypted = xorEncryptDecrypt(original, keyString);
        System.out.println("Encrypted: " + encrypted);

        decrypted = xorEncryptDecrypt(encrypted, keyString);
        System.out.println("Decrypted: " + decrypted); // Should return "Hello World!"
    }
}
