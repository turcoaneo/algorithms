package org.algorithms.test.copilot.ab;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class ABStringGenerator {
    public static void main(String[] args) throws IOException {
        int n = 100_000; // Length of the string
        int q = 1000;    // Number of queries (you can adjust this)

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Header: n and q
        sb.append(n).append(" ").append(q).append("\n");

        // Generate random A/B string
        for (int i = 0; i < n; i++) {
            sb.append(random.nextBoolean() ? 'A' : 'B');
        }
        sb.append("\n");

        // Generate dummy queries
        for (int i = 0; i < q; i++) {
            int l = random.nextInt(n - 10) + 1;
            int r = l + random.nextInt(Math.min(50, n - l));
            int k = random.nextInt(r - l + 1) + 1;
            sb.append(l).append(" ").append(r).append(" ").append(k).append("\n");
        }

        // Write to input.txt
        String file = "input_large2.txt";
        Files.writeString(Path.of(file), sb.toString());
        System.out.println("Generated " + file + " with random A/B string and queries.");
    }
}