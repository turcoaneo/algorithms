package org.algorithms.test.copilot.patterns.structural;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ProxyDemo {
    public static void main(String[] args) {
        SkiLiftProxy skiLift = new SkiLiftProxy();

        // Issue ski passes with different durations
        SkiLiftProxy.issuePass("Ovidiu", 4);  // 4-hour pass
        SkiLiftProxy.issuePass("Robert Lewandowski", 2); // 2-hour pass

        // Valid access cases
        skiLift.accessLift("Ovidiu");
        skiLift.accessLift("Robert Lewandowski");

        // Simulating expiration (for demo purposes, pretend time has passed)
        System.out.println("\n Simulating time lapse...");
        try { Thread.sleep(3000); } catch (InterruptedException e) {
            System.out.println("Error:" + e);
        } // Just a pause for realism

        // Attempt access after expiration
        skiLift.accessLift("Robert Lewandowski"); // Should be denied
    }
}

// Proxy: Controls Ski Lift Access Based on Time-Limited Passes
class SkiLiftProxy implements SkiLift {
    private final RealSkiLift realSkiLift = new RealSkiLift();
    private static final Map<String, LocalTime> passExpirationMap = new HashMap<>();

    // Grant ski pass for a specific duration
    public static void issuePass(String skierName, int durationHours) {
        LocalTime expirationTime = LocalTime.now().plusHours(durationHours);
        passExpirationMap.put(skierName, expirationTime);
        System.out.println("Issued a " + durationHours + "-hour ski pass for " + skierName + ", valid until " + expirationTime);
    }

    @Override
    public void accessLift(String skierName) {
        LocalTime expirationTime = passExpirationMap.get(skierName);

        if (expirationTime != null && LocalTime.now().isBefore(expirationTime)) {
            realSkiLift.accessLift(skierName); // Grant access
        } else {
            System.out.println(" Access Denied: " + skierName + "'s ski pass has expired!");
        }
    }
}// Real Object: Actual Ski Lift (Access Granted)
class RealSkiLift implements SkiLift {
    @Override
    public void accessLift(String skierName) {
        System.out.println("- " + skierName + " accessed the ski lift successfully!");
    }
}

// Subject Interface: Ski Lift Access
interface SkiLift {
    void accessLift(String skierName);
}
