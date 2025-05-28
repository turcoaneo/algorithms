package org.algorithms.test.copilot.leet.hard.sudoku;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("solveSudoku")
public class Solution {
    @TrackExecutionTime
    public void solveSudoku(char[][] board) {
        List<int[]> emptyCells = new ArrayList<>();

        // Precompute empty cell positions
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        // Solve with optimized recursion
        solve(board, emptyCells, 0);
    }

    private boolean solve(char[][] board, List<int[]> emptyCells, int index) {
        if (index == emptyCells.size()) return true; // All cells filled successfully

        int[] cell = emptyCells.get(index);
        int row = cell[0], col = cell[1];

        for (char num = '1'; num <= '9'; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solve(board, emptyCells, index + 1)) {
                    return true;
                }

                board[row][col] = '.'; // Backtrack
            }
        }
        return false; // No valid number found, backtrack
    }

    private boolean isValid(char[][] board, int row, int col, char num) {
        //noinspection DuplicatedCode
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num ||
                    board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num) {
                return false;
            }
        }
        return true;
    }
}