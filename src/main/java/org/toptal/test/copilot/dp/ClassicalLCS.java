package org.toptal.test.copilot.dp;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

@Component
public class ClassicalLCS {
    // Classical Dynamic Programming approach for Longest Common Subsequence
    @TrackExecutionTime
    public  String lcsClassical(String X, String Y) {
        int n = X.length();
        int m = Y.length();
        int[][] dp = new int[n + 1][m + 1];

        // Build the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char c1 = X.charAt(i - 1);
                char c2 = Y.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Reconstruct the LCS
        int i = n, j = m;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            char c1 = X.charAt(i - 1);
            char c = Y.charAt(j - 1);
            if (c1 == c) {
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.reverse().toString(); // Reverse to get correct LCS order
    }

    public static void main(String[] args) {
        String X = "AIR";
        String Y = "FAIRY";
        System.out.println("LCS: " + new ClassicalLCS().lcsClassical(X, Y));
    }
}