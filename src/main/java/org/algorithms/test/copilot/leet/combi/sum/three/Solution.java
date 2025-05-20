package org.algorithms.test.copilot.leet.combi.sum.three;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int start, int k, int target, List<Integer> tempList, List<List<Integer>> results) {
        if (target == 0 && tempList.size() == k) {
            results.add(new ArrayList<>(tempList)); // **Valid combination found**
            return;
        }
        if (target < 0 || tempList.size() >= k) return; // **Terminate if sum exceeds or limit is reached**

        for (int i = start; i <= 9; i++) {
            tempList.add(i); // **Choose current number**
            backtrack(i + 1, k, target - i, tempList, results); // **Recursive call with next number**
            tempList.remove(tempList.size() - 1); // **Backtrack to try next possibility**
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combinationSum3(3, 7)); // Output: [[1,2,4]]
        System.out.println(sol.combinationSum3(3, 9)); // Output: [[1,2,6],[1,3,5],[2,3,4]]
        System.out.println(sol.combinationSum3(4, 1)); // Output: []
    }
}