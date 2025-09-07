package org.algorithms.test.copilot.ab;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Component
public class ABStringSolution {
    @TrackExecutionTime
    public void solve(String input, String output) {
        Path inputPath = Path.of(input);
        Path outputPath = Path.of(output);

        try {
            List<String> lines = Files.readAllLines(inputPath);
            List<String> outputLines = getResults(lines);

            Files.write(outputPath, outputLines);
        } catch (IOException e) {
            System.err.println("Error handling files: " + e.getMessage());
        }
    }

    private List<String> getResults(List<String> lines) {
        if (lines.size() < 3) throw new IllegalArgumentException("Invalid input format");

        String space = " ";
        String[] firstLine = lines.get(0).split(space);
        int n = Integer.parseInt(firstLine[0]);
        int q = Integer.parseInt(firstLine[1]);
        String s = lines.get(1);

        List<String> outputLines = new ArrayList<>();

        processLines(lines, q, space, n, outputLines, s);

        return outputLines;
    }

    private void processLines(List<String> lines, int q, String space, int n, List<String> outputLines, String s) {
        for (int i = 0; i < q; i++) {
            String[] queryParts = lines.get(i + 2).split(space);
            int l = Integer.parseInt(queryParts[0]) - 1; // Convert to 0-based
            int r = Integer.parseInt(queryParts[1]) - 1;
            int k = Integer.parseInt(queryParts[2]) - 1;

            int targetIndex = l + k;
            if (targetIndex > r || targetIndex < l || k > r - l + 1 || r > n) {
                outputLines.add("-1");
                continue;
            }

            char targetChar = s.charAt(targetIndex);

            // Count how many times targetChar appears before targetIndex in [l, r]
            int count = 0;
            for (int j = l; j < targetIndex; j++) {
                if (s.charAt(j) == targetChar) count++;
            }

            char oppositeChar = (targetChar == 'A') ? 'B' : 'A';
            int matchIndex = getMatchIndex(l, r, s, oppositeChar, count);

            outputLines.add(matchIndex == -1 ? "-1" : String.valueOf(matchIndex));
        }
    }

    private int getMatchIndex(int l, int r, String s, char oppositeChar, int count) {
        // Find the count-th occurrence of oppositeChar in [l, r]
        int matchIndex = -1;
        int seen = 0;
        int countOppositeChar = 0;

        for (int j = l; j <= r; j++) {
            countOppositeChar++;
            char currentChar = s.charAt(j);
            if (currentChar == oppositeChar) {
                if (seen == count) {
                    matchIndex = countOppositeChar;
                    break;
                }
                seen++;
            }
        }
        return matchIndex;
    }

    public static void main(String[] args) {
//        String input = "input.txt";
//        String output = "output.txt";
        String input = "src/main/java/org/algorithms/test/copilot/ab/input.txt";
        String output = "src/main/java/org/algorithms/test/copilot/ab/output.txt";
        ABStringSolution sol = new ABStringSolution();
        sol.solve(input, output);
    }
}