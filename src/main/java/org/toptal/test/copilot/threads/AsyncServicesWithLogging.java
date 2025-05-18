package org.toptal.test.copilot.threads;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class AsyncServicesWithLogging {
    private static final Logger logger = Logger.getLogger(AsyncServicesWithLogging.class.getName());

    public static void main(String[] args) {
        fetchServiceA()
                .thenCompose(AsyncServicesWithLogging::fetchServiceB)
                .thenCompose(AsyncServicesWithLogging::fetchServiceC)
                .thenAccept(finalResult -> logger.info("Final Merged Response: " + finalResult)) // ✅ Logging instead of direct print
                .exceptionally(ex -> {
                    logger.severe("Exception Occurred: " + ex.getMessage()); // ✅ Error handling with logging
                    return null; // ✅ Ensures graceful failure without breaking execution
                });
    }

    // Service A - Starts the async chain
    private static CompletableFuture<String> fetchServiceA() {
        return CompletableFuture.supplyAsync(AsyncServicesWithLogging::simulateService);
    }

    public static CompletableFuture<String> fetchServiceB(String previousResult) {
        return CompletableFuture.completedFuture(simulateService("B", previousResult))
                .thenApply(result -> {
                    logger.info("Service B processed: " + result);
                    return result;
                });
    }

    public static CompletableFuture<String> fetchServiceC(String previousResult) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info("Entering Service C with input: " + previousResult);
            String result = simulateService("C", previousResult);
            logger.info("Service C output: " + result);
            return result;
        });
    }

    // Simulates external service behavior, optionally chaining previous results
    private static String simulateService() {
//        if (new Random().nextInt(10) < 2) throw new RuntimeException("Service A failed!");
        return "Service A";
    }

    private static String simulateService(String service, String previousResult) {
//        if (new Random().nextInt(10) < 2) throw new RuntimeException("Service " + service + " failed!");
        return previousResult + " -> Service " + service;
    }
}
