package org.algorithms.test.copilot.leet.hard.queens;

public class NQueensII {
    public static void main(String[] args) {
        SolutionIIBacktracking solutionIIBacktracking = new SolutionIIBacktracking();
        SolutionII solutionII = new SolutionII();

        for (int n = 1; n <= 9; n++) {
            int count = solutionIIBacktracking.totalNQueens(n);
            assert count == solutionII.totalNQueens(n);
            System.out.println("For n = " + n + ", solutions: " + count);
        }
    }
}