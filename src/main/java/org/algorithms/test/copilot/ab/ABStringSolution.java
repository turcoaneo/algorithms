package org.algorithms.test.copilot.ab;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class ABStringSolution {

    public void solve(String input, String output) {
        Path inputPath = Path.of(input);
        Path outputPath = Path.of(output);

        try (BufferedReader reader = Files.newBufferedReader(inputPath);
             BufferedWriter writer = Files.newBufferedWriter(outputPath)) {

            String[] firstLine = reader.readLine().split(" ");
            int n = Integer.parseInt(firstLine[0]);
            int q = Integer.parseInt(firstLine[1]);

            String s = reader.readLine();
            int[] a = new int[n];
            int[] b = new int[n];
            char[] charArray = s.toCharArray();

            a[0] = charArray[0] == 'A' ? 1 : 0;
            b[0] = charArray[0] == 'B' ? 1 : 0;

            for (int i = 1; i < n; i++) {
                a[i] = a[i - 1] + (charArray[i] == 'A' ? 1 : 0);
                b[i] = b[i - 1] + (charArray[i] == 'B' ? 1 : 0);
            }

            for (int i = 0; i < q; i++) {
                String[] queryParts = reader.readLine().split(" ");
                int l = Integer.parseInt(queryParts[0]) - 1;
                int r = Integer.parseInt(queryParts[1]) - 1;
                int k = Integer.parseInt(queryParts[2]) - 1;

                int targetIndex = l + k;
                if (targetIndex > r || targetIndex < l || k > r - l + 1 || r >= n) {
                    writer.write("-1");
                    writer.newLine();
                    continue;
                }

                char targetChar = s.charAt(targetIndex);
                int res = (targetChar == 'A')
                        ? processLine(l, r, targetIndex, a, b)
                        : processLine(l, r, targetIndex, b, a);

                writer.write(String.valueOf(res));
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error handling files: " + e.getMessage());
        }
    }

    private int processLine(int l, int r, int k, int[] f, int[] g) {
        int t = l == 0 ? 0 : f[l - 1];
        int p = l == 0 ? 0 : g[l - 1];
        int u = f[k] - t;
        int z = p + u;

        int v = lowerBound(g, z);
        if (v < 0 || v < l || v > r) return -1;
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
        new ABStringSolution().solve(input, output);
    }
}