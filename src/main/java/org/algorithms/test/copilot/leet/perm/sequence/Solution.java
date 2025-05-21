package org.algorithms.test.copilot.leet.perm.sequence;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("sequencePermutation")
public class Solution {
    @TrackExecutionTime
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int fact = 1;

        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fact *= i; // Compute factorial
        }

        k--; // Convert to zero-based index
        StringBuilder result = new StringBuilder();

        for (int i = n; i > 0; i--) {
            fact /= i;
            int index = k / fact; // Determine the right index
            result.append(nums.get(index));
            nums.remove(index);
            k %= fact; // Update k for next iteration
        }

        return result.toString();
    }
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.getPermutation(3, 3)); // Output: "213"
        System.out.println(sol.getPermutation(4, 9)); // Output: "2314"
        System.out.println(sol.getPermutation(3, 1)); // Output: "123"
    }
}