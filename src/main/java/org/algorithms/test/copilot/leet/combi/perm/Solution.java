package org.algorithms.test.copilot.leet.combi.perm;

import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> results = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int start, int n, int k, List<Integer> tempList, List<List<Integer>> results) {
        if (tempList.size() == k) {
            results.add(new ArrayList<>(tempList)); // **Store valid combination**
            return;
        }

        for (int i = start; i <= n; i++) {
            tempList.add(i); // **Choose current number**
            backtrack(i + 1, n, k, tempList, results); // **Recursive call with next number**
            tempList.remove(tempList.size() - 1); // **Backtrack to try next possibility**
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.combine(4, 2)); // Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
        System.out.println(sol.combine(1, 1)); // Output: [[1]]
    }
}