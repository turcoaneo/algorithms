package org.algorithms.test.copilot.leet.hard.queens;

import java.util.List;

public class NQueensTest {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: n = 1
        System.out.println("Solutions for n = 1:");
        printSolutions(solution.solveNQueens(1));

        // Test case 2: n = 4
        System.out.println("Solutions for n = 4:");
        printSolutions(solution.solveNQueens(4));

        // Test case 3: n = 8
        System.out.println("Solutions for n = 8:");
        printSolutions(solution.solveNQueens(8));
    }

    // Helper method to print solutions
    private static void printSolutions(List<List<String>> solutions) {
        for (List<String> board : solutions) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println(); // Separate different solutions
        }
        System.out.println("Total Solutions: " + solutions.size());
        System.out.println("---------------------------");
    }
}