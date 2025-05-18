package org.toptal.test.copilot.fundamentals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.threads.MatrixParallelSplit;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class ParametrizedMatrixTest {
    @Autowired
    private MatrixParallelSplit matrixParallelSplit;

    static Stream<Arguments> matrixProvider() {
        return Stream.of(
                Arguments.of(8, 4, 4, 2, 4, new Integer[]{31, 32}),
                Arguments.of(8, 8, 4, 4, 4, new Integer[]{61, 62, 63, 64}),
                Arguments.of(4, 4, 4, 1, 4, new Integer[]{16}),
                Arguments.of(3, 6, 3, 3, 2, new Integer[]{16, 17, 18}),
                Arguments.of(2, 4, 2, 2, 2, new Integer[]{7, 8})
        );
    }

    @ParameterizedTest
    @MethodSource("matrixProvider")
    void testMatrixMultiplication(int rows, int cols, int newRows, int newCols, int resultLength, Integer[] lastRow) {

        int[][] matrix = new int[rows][cols];
        int position = rows == cols ? rows : rows - cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (position > 0) {
                    matrix[i][j] = i * position + j + 1;
                } else {
                    matrix[i][j] = cols * i + j + 1;
                }
            }
        }

        List<Integer[][]> children = matrixParallelSplit.splitMatrix(matrix, newRows, newCols);
        int size = children.size();
        Assertions.assertEquals(resultLength, size);
        Integer[][] lastChildMatrix = children.get(size - 1);
        Assertions.assertArrayEquals(lastRow, lastChildMatrix[lastChildMatrix.length - 1]);
    }
}