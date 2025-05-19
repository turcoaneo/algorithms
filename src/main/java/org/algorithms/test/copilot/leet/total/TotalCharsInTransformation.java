package org.algorithms.test.copilot.leet.total;

import java.util.Arrays;
import java.util.List;


public class TotalCharsInTransformation {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcyy";
        int t = 2;
        List<Integer> nums = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2);
        int result = solution.bruteForceLengthAfterTransformations(s, t, nums);
        System.out.println("Result: " + result); //7
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result); //7

        s = "azbk";
        t = 1;
        nums = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2);
        result = solution.bruteForceLengthAfterTransformations(s, t, nums);
        System.out.println("Result: " + result); //8
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result); //8

        s = "zxyz";
        t = 3;
        nums = Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3);
        result = solution.bruteForceLengthAfterTransformations(s, t, nums);
        System.out.println("Result: " + result); //55
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result); //55

        s = "a";
        t = 1;
        nums = Arrays.asList(25, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        result = solution.bruteForceLengthAfterTransformations(s, t, nums);
        System.out.println("Result: " + result); //25
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result); //25

        s = "leetcode";
        t = 3;
        nums = Arrays.asList(1, 1, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        result = solution.bruteForceLengthAfterTransformations(s, t, nums);
        System.out.println("Result: " + result); //21
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result); //21

        s = "abcdefghijklmnopqrstuvwxy";
        t = 50;
        nums = Arrays.asList(1, 1, 4, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 5, 9, 100);
        result = solution.lengthAfterTransformationsString(s, t, nums);
        System.out.println("Result: " + result);
    }
}
