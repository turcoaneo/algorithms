package org.toptal.test.copilot.dp;
import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Component
public class CoinChange {
    @TrackExecutionTime
    public List<Integer> getCoinsUsed(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        int[] coinUsed = new int[amount + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        Arrays.fill(coinUsed, -1);
        dp[0] = 0; // Base case: 0 coins needed for amount 0

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    if (dp[i - coin] + 1 < dp[i]) {
                        dp[i] = dp[i - coin] + 1;
                        coinUsed[i] = coin; // Store the coin chosen
                    }
                }
            }
        }

        // If amount isn't reachable, return an empty list
        if (dp[amount] == Integer.MAX_VALUE) {
            return List.of(); // No solution
        }

        // Backtrack to print the coins used
        List<Integer> result = new ArrayList<>();
        while (amount > 0) {
            result.add(coinUsed[amount]);
            amount -= coinUsed[amount];
        }

        return result; // List of coins used
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        int amount = 6;
        System.out.println(new CoinChange().getCoinsUsed(amount, coins)); // Output: [3, 3]
    }
}
