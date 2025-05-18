package org.algorithms.test.copilot.dp;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.ArrayList;
import java.util.List;

@Component
public class WaysToMakeChange {

    @TrackExecutionTime
    public int countWaysToMakeChange(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // Only one way to make 0€: by choosing no coins

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin]; // Add ways to make (amount - coin)
            }
        }

        return dp[amount]; // Total ways to make 'amount'
    }


    @TrackExecutionTime
    public List<List<Integer>> findCombinations(int amount, int[] coins) {
        //noinspection unchecked
        List<List<Integer>>[] dp = new List[amount + 1];
        for (int i = 0; i <= amount; i++) {
            dp[i] = new ArrayList<>();
        }

        dp[0].add(new ArrayList<>()); // Base case: 1 way to make 0€ (empty list)

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                for (List<Integer> combination : dp[i - coin]) {
                    List<Integer> newCombination = new ArrayList<>(combination);
                    newCombination.add(coin);
                    dp[i].add(newCombination);
                }
            }
        }

        return dp[amount]; // Return list of valid combinations
    }

    public static void main(String[] args) {
        int[] coins = {1, 3, 4};
        int amount = 6;
        int counter = new WaysToMakeChange().countWaysToMakeChange(amount, coins);
        System.out.println("Counter: " + counter);

        List<List<Integer>> ways = new WaysToMakeChange().findCombinations(amount, coins);
        // Print all ways to make the given amount
        for (List<Integer> way : ways) {
            System.out.println(way);
        }
    }
}
