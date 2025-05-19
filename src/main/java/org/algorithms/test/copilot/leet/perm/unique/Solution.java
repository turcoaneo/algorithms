package org.algorithms.test.copilot.leet.perm.unique;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component("permuteUnique")
public class Solution {
    @TrackExecutionTime
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums); // **Sort to handle duplicates**
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> tempList, List<List<Integer>> results) {
        if (tempList.size() == nums.length) {
            results.add(new ArrayList<>(tempList)); // **Add valid permutation**
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // **Skip used elements**
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue; // **Skip duplicates**

            used[i] = true;
            tempList.add(nums[i]);
            backtrack(nums, used, tempList, results);
            tempList.remove(tempList.size() - 1);
            used[i] = false;
        }
    }
}