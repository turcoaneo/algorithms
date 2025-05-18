package org.algorithms.test.copilot.leet.total.squareful;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component("numSquarefulPerms")
public class Solution {

    @TrackExecutionTime
    public int numSquarefulPerms(int[] nums) {
        Arrays.sort(nums); // Sorting helps avoid duplicate permutations
        Map<Integer, List<Integer>> graph = buildGraph(nums);
        boolean[] used = new boolean[nums.length];

        return backtrack(nums, used, -1, 0, graph);
    }

    private Map<Integer, List<Integer>> buildGraph(int[] nums) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            graph.putIfAbsent(nums[i], new ArrayList<>());
            for (int j = 0; j < nums.length; j++) {
                if (i != j && isPerfectSquare(nums[i] + nums[j])) {
                    graph.get(nums[i]).add(nums[j]);
                }
            }
        }
        return graph;
    }

    private int backtrack(int[] nums, boolean[] used, int prev, int count, Map<Integer, List<Integer>> graph) {
        if (count == nums.length) return 1;
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue; // Skip duplicates
            if (prev == -1 || graph.get(prev).contains(nums[i])) { // Check valid connection
                used[i] = true;
                total += backtrack(nums, used, nums[i], count + 1, graph);
                used[i] = false; // Backtrack
            }
        }
        return total;
    }

    private boolean isPerfectSquare(int sum) {
        int root = (int) Math.sqrt(sum);
        return root * root == sum;
    }

    private int backtrack(int[] nums, boolean[] used, int prev, int count, Map<Integer, List<Integer>> graph, List<Integer> current) {
        if (count == nums.length) {
            System.out.println(current); // 🖨 Print each valid permutation!
            return 1;
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) continue; // Skip duplicates

            if (prev == -1 || graph.get(prev).contains(nums[i])) { // Check valid connection
                used[i] = true;
                current.add(nums[i]); // ✅ Add number to current permutation

                total += backtrack(nums, used, nums[i], count + 1, graph, current);

                current.remove(current.size() - 1); // ❌ Remove last element (backtrack)
                used[i] = false;
            }
        }
        return total;
    }
}
