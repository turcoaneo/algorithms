package org.algorithms.test.copilot.leet.hard.match.regex;

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // Empty string matches empty pattern

        // Initialize DP table for patterns with '*'
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*' && j > 1) {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1), pc = p.charAt(j - 1);

                if (pc == '.' || sc == pc) {
                    dp[i][j] = dp[i - 1][j - 1]; // Match single character
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j - 2]; // Match zero occurrences of previous char
                    if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == sc) {
                        dp[i][j] |= dp[i - 1][j]; // Match one or more occurrences
                    }
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        Solution solver = new Solution();
        System.out.println(solver.isMatch("aa", "a"));  // false
        System.out.println(solver.isMatch("aa", "a.")); // true
        System.out.println(solver.isMatch("ab", ".*")); // true
    }
}