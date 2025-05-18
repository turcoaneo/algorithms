package org.toptal.test.copilot.dp;
import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.HashMap;
import java.util.Map;

@Component
public class SparseLCS {
    // Sparse Dynamic Programming for LCS
    // Only stores necessary entries, optimizing memory usage
    @TrackExecutionTime
    public int lcsSparseDP(String X, String Y) {
        int n = X.length();
        int m = Y.length();

        // Using a HashMap to store only relevant entries
        Map<String, Integer> dp = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                String key = i + "," + j; // Unique key for (i, j)

                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp.put(key, dp.getOrDefault((i - 1) + "," + (j - 1), 0) + 1);
                } else {
                    dp.put(key, Math.max(
                            dp.getOrDefault((i - 1) + "," + j, 0),
                            dp.getOrDefault(i + "," + (j - 1), 0)
                    ));
                }
            }
        }

        // LCS length at (n, m)
        return dp.getOrDefault(n + "," + m, 0);
    }

    public static void main(String[] args) {
        String X = "AGGTAB";
        String Y = "GXTXAYB";

        int lcsLength = new SparseLCS().lcsSparseDP(X, Y);
        System.out.println("Sparse DP LCS Length: " + lcsLength);
    }
}