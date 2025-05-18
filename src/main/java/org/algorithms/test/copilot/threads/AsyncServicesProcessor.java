package org.algorithms.test.copilot.threads;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class AsyncServicesProcessor {
    private static final Logger logger = Logger.getLogger(AsyncServicesProcessor.class.getName());
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    private static final Random random = new Random();
    private static final ConcurrentHashMap<String, ServiceResponse> dataMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        populateMap(); // Fill the map with mock data

        fetchServiceA()
                .thenCompose(AsyncServicesProcessor::fetchServiceB)
                .thenCompose(AsyncServicesProcessor::fetchServiceC)
                .thenAccept(AsyncServicesProcessor::processResponse)
                .exceptionally(ex -> {
                    logger.severe("Exception Occurred: " + ex.getMessage());
                    return null;
                });
    }

    // Service A - Returns a String
    private static CompletableFuture<String> fetchServiceA() {
        return CompletableFuture.supplyAsync(() -> "Service A");
    }

    // Service B - Retrieves a mapped value and returns a ServiceResponse
    private static CompletableFuture<ServiceResponse> fetchServiceB(String previousResult) {
        return CompletableFuture.completedFuture(simulateService(previousResult))
                .thenApply(result -> {
                    logger.info("Service B processed: " + result);
                    return new ServiceResponse(result, random.nextInt(100));
                });
    }

    private static CompletableFuture<String> fetchServiceC(ServiceResponse response) {
        return CompletableFuture.supplyAsync(() -> {
            String name = response.name();
            logger.info("Entering Service C with input: " + name);

            String value = String.valueOf(response.value());
            String jsonFormat = "{ \"service\": \"safeName\", \"value\": res }";
            String replaced = jsonFormat.replace("safeName", name).replace("res", value);

            logger.info("Service C output: " + replaced.replace("\"", ""));
            return replaced;
        });
    }

    // Detects format & extracts meaningful data
    private static void processResponse(String response) {
        if (response.startsWith("{")) {
            extractFromJson(response);
        } else {
            extractFromXml(response);
        }
    }

    // Extracts meaningful data from JSON response
    private static void extractFromJson(String jsonResponse) {
        try {
            JsonNode node = jsonMapper.readTree(jsonResponse);
            String serviceName = node.get("service").asText();
            int value = node.get("value").asInt();
            logger.info("Extracted JSON -> Service: " + serviceName + ", Value: " + value);
        } catch (Exception e) {
            logger.severe("Error processing JSON: " + e.getMessage());
        }
    }

    // Extracts meaningful data from XML response
    private static void extractFromXml(String xmlResponse) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new org.xml.sax.InputSource(new StringReader(xmlResponse)));
            String serviceName = doc.getElementsByTagName("name").item(0).getTextContent();
            int value = Integer.parseInt(doc.getElementsByTagName("value").item(0).getTextContent());
            logger.info("Extracted XML -> Service: " + serviceName + ", Value: " + value);
        } catch (Exception e) {
            logger.severe("Error processing XML: " + e.getMessage());
        }
    }

    // Populates mock data for Service B
    private static void populateMap() {
        dataMap.put("Service A", new ServiceResponse("Service B", 100));
    }

    // Simulates external service behavior
    private static String simulateService(String previousResult) {
        ServiceResponse result = dataMap.getOrDefault(previousResult, new ServiceResponse("Default Service B", 0));
        logger.info("Simulating Service: B" + " | Input: " + result);
        return result.toString();
    }
}

// Helper class to represent Service B's response
record ServiceResponse(String name, int value) {
}