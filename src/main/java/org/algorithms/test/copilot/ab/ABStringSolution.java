package org.algorithms.test.copilot.ab;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Component
public class ABStringSolution {
    //    @TrackExecutionTime
    public void solve(String input, String output) {
        Path inputPath = Path.of(input);
        Path outputPath = Path.of(output);

        try {
            List<String> lines = Files.readAllLines(inputPath);
            writeResults(outputPath, getResults(lines));
        } catch (IOException e) {
            System.err.println("Error handling files: " + e.getMessage());
        }
    }

    public void writeResults(Path outputPath, List<Integer> results) {
        StringBuilder builder = new StringBuilder(results.size() * 4); // Rough estimate: 3 digits + newline

        for (int result : results) {
            builder.append(result).append('\n');
        }

        try {
            Files.writeString(outputPath, builder.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error writing results: " + e.getMessage());
        }
    }

    private List<Integer> getResults(List<String> lines) {
        if (lines.size() < 3) throw new IllegalArgumentException("Invalid input format");

        String space = " ";
        String[] firstLine = lines.get(0).split(space);
        int n = Integer.parseInt(firstLine[0]);
        int q = Integer.parseInt(firstLine[1]);
        String s = lines.get(1);

        return processLines(lines, n, q, s);
    }

    public List<Integer> processLines(List<String> lines, int n, int q, String s) {
        List<Integer> outputLines = new ArrayList<>();
        int[] a = new int[n];
        int[] b = new int[n];
        char[] charArray = lines.get(1).toCharArray();
        a[0] = charArray[0] == 'A' ? 1 : 0;
        b[0] = charArray[0] == 'B' ? 1 : 0;
        for (int i = 1; i < charArray.length; i++) {
            if (charArray[i] == 'A') {
                a[i] = a[i - 1] + 1;
                b[i] = b[i - 1];
            } else {
                b[i] = b[i - 1] + 1;
                a[i] = a[i - 1];
            }
        }

        for (int i = 0; i < q; i++) {
            String[] queryParts = lines.get(i + 2).split(" ");
            int l = Integer.parseInt(queryParts[0]) - 1; // Convert to 0-based
            int r = Integer.parseInt(queryParts[1]) - 1;
            int k = Integer.parseInt(queryParts[2]) - 1;

            int targetIndex = l + k;
            if (targetIndex > r || targetIndex < l || k > r - l + 1 || r > n) {
                outputLines.add(-1);
                continue;
            }

            char targetChar = s.charAt(targetIndex);
            int res;
            if (targetChar == 'A') {
                res = processLine(l, r, targetIndex, a, b);
            } else {
                res = processLine(l, r, targetIndex, b, a);
            }
            outputLines.add(res);
        }
        return outputLines;
    }

    private int processLine(int l, int r, int k, int[] f, int[] g) {
        int t = l == 0 ? 0 : f[l - 1];
        int p = l == 0 ? 0 : g[l - 1];
        int u = f[k] - t;
        int z = p + u;

        int v = lowerBound(g, z);
        if (v < 0) return -1;

        if (v < l || v > r) return -1;
        return v - l + 1;
    }

    public int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left < arr.length ? left : -1;
    }

    public static void main(String[] args) {
        String input = "input.txt";
        String output = "output.txt";
        ABStringSolution sol = new ABStringSolution();
        sol.solve(input, output);
    }
}