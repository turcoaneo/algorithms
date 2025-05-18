package org.algorithms.test.copilot.patterns.creational;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;

public class SecureSingleton implements Serializable {

    private static final long serialVersionUID = 1L;
    private transient SecretKey secretKey; // Prevent key from serialization

    private SecureSingleton() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            secretKey = keyGen.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate encryption key", e);
        }
    }

    private static class SingletonHelper {
        private static final SecureSingleton INSTANCE = new SecureSingleton();
    }

    public static SecureSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Ensure deserialized instance is same as original
    protected Object readResolve() {
        return SingletonHelper.INSTANCE;
    }

    // Encrypt & Serialize Singleton
    public void saveSingleton(String filePath) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(cipher.doFinal("SingletonData".getBytes())); // Encrypt before saving
        }
    }

    // Decrypt & Deserialize Singleton
    public void loadSingleton(String filePath) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            byte[] encryptedData = (byte[]) in.readObject();
            System.out.println("Decrypted Data: " + new String(cipher.doFinal(encryptedData)));
        }
    }
}
