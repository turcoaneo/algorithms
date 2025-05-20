package org.algorithms.test.copilot.leet.combi.sum;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> tempList, List<List<Integer>> results) {
        if (target == 0) {
            results.add(new ArrayList<>(tempList)); // **Valid combination found**
            return;
        }
        if (target < 0) return; // **Terminate if sum exceeds target**

        for (int i = start; i < candidates.length; i++) {
            tempList.add(candidates[i]); // **Choose current number**
            backtrack(candidates, target - candidates[i], i, tempList, results); // **Recursive call allowing repetition**
            tempList.remove(tempList.size() - 1); // **Backtrack to explore next choice**
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combinationSum(new int[]{2, 3, 6, 7}, 7)); // Output: [[2,2,3],[7]]
        System.out.println(sol.combinationSum(new int[]{2, 3, 5}, 8)); // Output: [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println(sol.combinationSum(new int[]{2}, 1)); // Output: []
    }
}