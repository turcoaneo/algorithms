package org.algorithms.test.copilot.leet.hard.queens;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Solution {
    @TrackExecutionTime
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // row - col
        boolean[] diag2 = new boolean[2 * n]; // row + col
        char[][] board = new char[n][n];

        for (char[] row : board) {
            java.util.Arrays.fill(row, '.'); // Initialize empty board
        }

        backtrack(0, n, board, results, cols, diag1, diag2);
        return results;
    }

    private void backtrack(int row, int n, char[][] board, List<List<String>> results, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            results.add(convertBoard(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n] || diag2[row + col]) continue;

            board[row][col] = 'Q';
            cols[col] = true;
            diag1[row - col + n] = true;
            diag2[row + col] = true;

            backtrack(row + 1, n, board, results, cols, diag1, diag2);

            board[row][col] = '.';
            cols[col] = false;
            diag1[row - col + n] = false;
            diag2[row + col] = false;
        }
    }

    private List<String> convertBoard(char[][] board) {
        List<String> formattedBoard = new ArrayList<>();
        for (char[] row : board) {
            formattedBoard.add(new String(row));
        }
        return formattedBoard;
    }
}