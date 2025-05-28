package org.algorithms.test.copilot.leet.hard.parentheses;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("longestValidParentheses")
public class Solution {
    @TrackExecutionTime
    public int longestValidParentheses(String s) {
        if (s == null || s.isEmpty()) return 0;

        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}