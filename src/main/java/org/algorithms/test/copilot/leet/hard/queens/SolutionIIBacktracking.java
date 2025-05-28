package org.algorithms.test.copilot.leet.hard.queens;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component
public class SolutionIIBacktracking {
    private int count;

    @TrackExecutionTime
    public int totalNQueens(int n) {
        count = 0;
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col
        boolean[] diag2 = new boolean[2 * n]; // row + col

        backtrack(0, n, cols, diag1, diag2);
        return count;
    }

    private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n] || diag2[row + col]) continue;

            cols[col] = true;
            diag1[row - col + n] = true;
            diag2[row + col] = true;

            backtrack(row + 1, n, cols, diag1, diag2);

            cols[col] = false;
            diag1[row - col + n] = false;
            diag2[row + col] = false;
        }
    }
}