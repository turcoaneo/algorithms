package org.algorithms.test.copilot.leet.hard.ugly;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("nthUglyNumber")
public class Solution {
    @TrackExecutionTime
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0] = 1; // First ugly number
        
        int p2 = 0, p3 = 0, p5 = 0; // Three pointers
        
        for (int i = 1; i < n; i++) {
            int nextUgly = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
            dp[i] = nextUgly;
            
            if (nextUgly == dp[p2] * 2) p2++; // Increment pointer if used
            if (nextUgly == dp[p3] * 3) p3++;
            if (nextUgly == dp[p5] * 5) p5++;
        }
        
        return dp[n - 1];
    }
}