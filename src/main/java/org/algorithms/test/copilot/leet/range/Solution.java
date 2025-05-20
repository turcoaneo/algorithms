package org.algorithms.test.copilot.leet.range;

import java.util.Arrays;

class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int totalSubArrays = n * (n + 1) / 2;
        int[] subArraySums = new int[totalSubArrays];
        int index = 0;
        
        // Generate all subarray sums using array
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                subArraySums[index++] = sum;
            }
        }

        // Sort subarray sums
        Arrays.sort(subArraySums);

        // Compute the sum between left and right indices
        int result = 0;
        int MODULO = 1_000_000_007;
        for (int i = left - 1; i < right; i++) {
            result = (result + subArraySums[i]) % MODULO;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        //noinspection DuplicatedCode
        System.out.println(sol.rangeSum(new int[]{1,2,3,4}, 4, 1, 5)); // Output: 13
        System.out.println(sol.rangeSum(new int[]{1,2,3,4}, 4, 3, 4)); // Output: 6
        System.out.println(sol.rangeSum(new int[]{1,2,3,4}, 4, 1, 10)); // Output: 50
    }
}