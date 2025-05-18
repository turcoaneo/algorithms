package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.threads.MatrixParallelSplit;

import java.util.List;


@SpringBootTest
public class MultiProcessingTests {
    @Autowired
    private MatrixParallelSplit matrixParallelSplit;

    @Test
    void testArrayIterativeQuickSortingHoare() {
        int M = 8;
        int N = 4;

        int[][] matrix = new int[M][N];
        int position = M == N ? M : M - N;
        //Sample data
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = i * position + j + 1;
            }
        }

        int m = 2, n = 4;

        List<Integer[][]> children = matrixParallelSplit.splitMatrix(matrix, m, n);
        Assertions.assertNotNull(children);
    }
}