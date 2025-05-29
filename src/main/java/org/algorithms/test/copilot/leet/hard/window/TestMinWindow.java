package org.algorithms.test.copilot.leet.hard.window;

public class TestMinWindow {
    public static void main(String[] args) {
        SolutionMap solutionMap = new SolutionMap();

        System.out.println(solutionMap.minWindow("ADOBECODEBANC", "ABC")); // Output: "BANC"
        System.out.println(solutionMap.minWindow("a", "a")); // Output: "a"
        System.out.println(solutionMap.minWindow("a", "aa")); // Output: ""
        System.out.println(solutionMap.minWindow("aa", "aa")); // Output: "aa"
        System.out.println(solutionMap.minWindow("ab", "A")); // Output: ""
    }
}