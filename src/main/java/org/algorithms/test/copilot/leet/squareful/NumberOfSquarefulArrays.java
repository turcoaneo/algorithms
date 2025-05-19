package org.algorithms.test.copilot.leet.squareful;

public class NumberOfSquarefulArrays {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.numSquarefulPerms(new int[]{1, 17, 8});
        System.out.println("NumberOfSquarefulArrays {1, 17, 8}: " + result);

        result = solution.numSquarefulPerms(new int[]{2,2,2});
        System.out.println("NumberOfSquarefulArrays {2,2,2}: " + result);
    }
}
