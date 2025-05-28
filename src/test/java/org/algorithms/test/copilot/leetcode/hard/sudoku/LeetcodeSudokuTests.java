package org.algorithms.test.copilot.leetcode.hard.sudoku;

import org.algorithms.test.copilot.leet.hard.sudoku.BruteRecursion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeSudokuTests {
    @Autowired
    BruteRecursion bruteRecursion;
    @Autowired
    org.algorithms.test.copilot.leet.hard.sudoku.Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(
                        new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8'
                                , '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
                        new char[][]{{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8'
                                , '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                                {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
                        new char[][]{{'5', '3', '4', '6', '7', '8', '9', '1', '2'}, {'6', '7', '2', '1', '9', '5', '3', '4', '8'}, {'1',
                                '9', '8', '3', '4', '2', '5', '6', '7'}, {'8', '5', '9', '7', '6', '1', '4', '2', '3'}, {'4', '2', '6', '8', '5', '3', '7',
                                '9', '1'}, {'7', '1', '3', '9', '2', '4', '8', '5', '6'}, {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                                {'2', '8', '7', '4', '1', '9', '6', '3', '5'}, {'3', '4', '5', '2', '8', '6', '1', '7', '9'}})
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(char[][] input, char[][] input2, char[][] output) {
        bruteRecursion.solveSudoku(input);
        solution.solveSudoku(input2);
        int i = 0;
        for (char[] row : output) {
            Assertions.assertArrayEquals(row, input[i]);
            Assertions.assertArrayEquals(row, input2[i]);
            i++;
        }
    }
}