package org.algorithms.test.copilot.leet.range;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.PriorityQueue;

@Component
public class PrioritySolution {
    @TrackExecutionTime
    public int rangeSum(int[] nums, int n, int left, int right) {
        int MODULO = 1_000_000_007;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Compute prefix sums
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                minHeap.add(sum);
            }
        }

        // Extract and sum relevant values from left to right
        int result = 0;
        for (int i = 1; i <= right; i++) {
            //noinspection DataFlowIssue
            int value = minHeap.poll(); // Extract min value
            if (i >= left) {
                result = (result + value) % MODULO;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        PrioritySolution sol = new PrioritySolution();
        //noinspection DuplicatedCode
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 1, 5)); // Output: 13
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 3, 4)); // Output: 6
        System.out.println(sol.rangeSum(new int[]{1, 2, 3, 4}, 4, 1, 10)); // Output: 50
    }
}