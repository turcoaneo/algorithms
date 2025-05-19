package org.algorithms.test.copilot.leet.perm.next;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("nextPermutation")
public class Solution {
    @TrackExecutionTime
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // **Step 1: Find pivot (first decreasing element from right)**
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) { // **Step 2: Find next larger element & swap**
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // **Step 3: Reverse suffix after pivot to get next permutation**
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}