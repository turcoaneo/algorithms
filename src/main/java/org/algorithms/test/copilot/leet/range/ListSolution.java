package org.algorithms.test.copilot.leet.range;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ListSolution {
    @TrackExecutionTime
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> subarraySums = new ArrayList<>();

        // Generate all subarray sums
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                subarraySums.add(sum);
            }
        }

        // Sort subarray sums
        Collections.sort(subarraySums);

        // Compute the sum between left and right indices
        int result = 0;
        int MODULO = 1_000_000_007;
        for (int i = left - 1; i < right; i++) {
            result = (result + subarraySums.get(i)) % MODULO;
        }

        return result;
    }

    public static void main(String[] args) {
        ListSolution sol = new ListSolution();
        //noinspection DuplicatedCode
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 1, 5)); // Output: 13
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 3, 4)); // Output: 6
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 1, 10)); // Output: 50
    }
}