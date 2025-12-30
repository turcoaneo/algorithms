package org.algorithms.test.copilot.tutorial;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("minPositiveInteger")
public class MinPositiveInteger {
    public record Maxes(int upper, int prev) {
    }

    public Maxes getMaxes(int[] A) {
        int len = A.length;
        if (len < 2) {
            return new Maxes(1, 0);
        }
        int max = Math.max(A[0], A[1]);
        int max2 = Math.min(A[0], A[1]);

        for (int i = 2; i < len; i++) {
            if (A[i] > max) {
                max2 = max;
                max = A[i];
            }
        }

        return new Maxes(max, max2);
    }

    @TrackExecutionTime
    public int getMinPositiveInteger(int[] A) {
        if (A.length < 2) {
            return -1;
        }
        Maxes maxes = getMaxes(A);
        int upper = maxes.upper();
        int prev = maxes.prev();
        if (upper <= 0) {
            return 1;
        }
        if (prev <= 0) {
            if (upper == 1) {
                return -1;
            }
            return 1;
        }
        if (upper - prev > 1) {
            return prev + 1;
        }
        return upper + 1;
    }

    public static void main(String[] args) {
        MinPositiveInteger minPositiveInteger = new MinPositiveInteger();
        int result = minPositiveInteger.getMinPositiveInteger(new int[]{-2, -1, 1});
        System.out.printf("Result = %d; ", result);
        result = minPositiveInteger.getMinPositiveInteger(new int[]{-2, -1, 2});
        System.out.printf("Result = %d.", result);
    }
}