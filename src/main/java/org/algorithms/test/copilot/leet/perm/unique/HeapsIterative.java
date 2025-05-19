package org.algorithms.test.copilot.leet.perm.unique;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HeapsIterative {
    @TrackExecutionTime
    public List<List<Integer>> permuteUnique(int[] nums) {
        Set<List<Integer>> uniqueResults = new HashSet<>();
        int n = nums.length;
        int[] c = new int[n]; // Swap count tracker
        Arrays.fill(c, 0);

        uniqueResults.add(toList(nums)); // **Add initial permutation**

        int i = 0;
        while (i < n) {
            if (c[i] < i) { // **Swap elements based on parity**
                int swapIndex = (i % 2 == 0) ? 0 : c[i];
                swap(nums, swapIndex, i);

                List<Integer> perm = toList(nums);
                uniqueResults.add(perm); // **Store unique permutation**

                c[i]++;
                i = 0; // **Reset index**
            } else {
                c[i] = 0;
                i++;
            }
        }

        return new ArrayList<>(uniqueResults);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);
        return list;
    }

    public static void main(String[] args) {
        HeapsIterative hiu = new HeapsIterative();
        int[] nums = {1, 1, 2};
        System.out.println(hiu.permuteUnique(nums));
    }
}