package org.algorithms.test.copilot.patterns.creational;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SingletonTest {
    public static void main(String[] args) throws Exception {
        SecureSingleton instance1 = SecureSingleton.getInstance();

        // Serialize Singleton
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"));
        out.writeObject(instance1);
        out.close();

        // Deserialize Singleton
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"));
        SecureSingleton instance2 = (SecureSingleton) in.readObject();
        in.close();

        // Validate Singleton property
        System.out.println("Are both instances the same? " + (instance1 == instance2)); // Should print "true"
    }
}
