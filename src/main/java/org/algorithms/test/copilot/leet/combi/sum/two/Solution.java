package org.algorithms.test.copilot.leet.combi.sum.two;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates); // **Sort to handle duplicates effectively**
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
            if (i > start && candidates[i] == candidates[i - 1]) continue; // **Skip duplicate elements**
            
            tempList.add(candidates[i]); // **Choose current number**
            backtrack(candidates, target - candidates[i], i + 1, tempList, results); // **Move to next index**
            tempList.remove(tempList.size() - 1); // **Backtrack to explore next choice**
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8)); // Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
        System.out.println(sol.combinationSum2(new int[]{2,5,2,1,2}, 5)); // Output: [[1,2,2],[5]]
    }
}