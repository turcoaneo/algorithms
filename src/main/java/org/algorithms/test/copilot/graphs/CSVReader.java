package org.algorithms.test.copilot.graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class CSVReader {
    public static List<EdgeCSV> loadEdgesFromCSV(String filePath) {
        List<EdgeCSV> edges = new ArrayList<>();

        populateEdges(filePath, edges);

        return edges;
    }

    public static List<EdgeCSV> loadEdgesFromCSVBuffered(String filePath) {
        List<EdgeCSV> edges = new ArrayList<>();

        populateEdgesBuffered(filePath, edges);

        return edges;
    }

    private static void populateEdgesBuffered(String filePath, List<EdgeCSV> edges) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fillIn(edges, line);
            }
        } catch (IOException e) {
            printEx(e);
        }
    }

    private static void printEx(IOException e) {
        System.out.println("Something wrong: " + e);
    }

    private static void fillIn(List<EdgeCSV> edges, String line) {
        String separator = ",";
        String[] values = line.split(separator); // Assuming CSV uses commas as delimiters
        int src = Integer.parseInt(values[0].trim());
        int dest = Integer.parseInt(values[1].trim());
        int weight = Integer.parseInt(values[2].trim());
        edges.add(new EdgeCSV(src, dest, weight)); // Add edge to the list
    }

    private static void populateEdges(String filePath, List<EdgeCSV> edges) {
        try {
            //noinspection resource
            Files.lines(Paths.get(filePath)).forEach(line -> fillIn(edges, line));
        } catch (IOException e) {
            printEx(e);
        }


    }

    public static void main(String[] args) {
        String filePath = "src/main/java/org/algorithms/test/copilot/graphs/edges.csv";
        List<EdgeCSV> edges = loadEdgesFromCSVBuffered(filePath);

        System.out.println("Loaded Edges:");
        for (EdgeCSV e : edges) {
            System.out.println("(" + e.src + ", " + e.dest + ", " + e.weight + ")");
        }
    }
}