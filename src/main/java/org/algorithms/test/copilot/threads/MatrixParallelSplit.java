package org.algorithms.test.copilot.threads;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class MatrixTask extends RecursiveTask<int[][]> {
    private final int[][] matrix;
    private final int startRow, endRow, startCol, endCol;

    public MatrixTask(int[][] matrix, int startRow, int endRow, int startCol, int endCol) {
        this.matrix = matrix;
        this.startRow = startRow;
        this.endRow = endRow;
        this.startCol = startCol;
        this.endCol = endCol;
    }

    @Override
    protected int[][] compute() {
        int[][] result = new int[endRow - startRow][endCol - startCol];

        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                result[i - startRow][j - startCol] = calculateBlur(matrix, i, j);
            }
        }

        System.out.println("Processed Submatrix (" + startRow + ":" + endRow + ", " + startCol + ":" + endCol + "):");
        printMatrix(result);

        return result;
    }

    private int calculateBlur(int[][] matrix, int row, int col) {
        int sum = 0, count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < matrix.length && j >= 0 && j < matrix[0].length) { // Bounds check
                    sum += matrix[i][j];
                    count++;
                }
            }
        }

        return sum / count; // Return average value
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }
}

// Wrapper class for ForkJoinPool
class ForkJoinPoolWrapper implements AutoCloseable {
    private final ForkJoinPool pool;

    public ForkJoinPoolWrapper() {
        this.pool = new ForkJoinPool();
    }

    public <T> void invoke(ForkJoinTask<T> task) {
        pool.invoke(task);
    }

    @Override
    public void close() {
        pool.shutdown();
    }
}

@Component
public class MatrixParallelSplit {


    @TrackExecutionTime
    public void processForkJoinPool(int N, int M, int[][] matrix) {
        if (M % N != 0) {
            System.out.println("Error: N must be a denominator of M");
            return;
        }
        // Using try-with-resources for ForkJoinPool
        try (ForkJoinPoolWrapper pool = new ForkJoinPoolWrapper()) {

            MatrixTask[][] tasks = createAndInvokeTasks(N, M, matrix, pool);

            int[][] finalMatrix = reassembleMatrix(N, M, tasks);

            System.out.println("Final Merged Matrix:");
            printMatrix(finalMatrix);
        }
    }

    private int[][] reassembleMatrix(int N, int M, MatrixTask[][] tasks) {
        int[][] finalMatrix = new int[M][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[][] processed = tasks[i][j].join();
                for (int r = 0; r < processed.length; r++) {
                    System.arraycopy(processed[r], 0, finalMatrix[r + i * (M / N)], j * (M / N), processed[r].length);
                }
            }
        }
        return finalMatrix;
    }

    private MatrixTask[][] createAndInvokeTasks(int N, int M, int[][] matrix, ForkJoinPoolWrapper pool) {
        MatrixTask[][] tasks = new MatrixTask[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int startRow = i * (M / N);
                int endRow = (i + 1) * (M / N);
                int startCol = j * (M / N);
                int endCol = (j + 1) * (M / N);

                tasks[i][j] = new MatrixTask(matrix, startRow, endRow, startCol, endCol);
                pool.invoke(tasks[i][j]);
            }
        }
        return tasks;
    }

    @TrackExecutionTime
    public List<Integer[][]> splitMatrix(int[][] matrix, int newRows, int newCols) {
        int iterRow, iterCol, rows = matrix.length;
        if (rows % newRows != 0) {
            System.out.println("Split matrix should have rows as multiple of child rows");
            return null;
        } else {
            iterRow = (rows / newRows);
        }
        int cols = matrix[0].length;
        if (cols % newCols != 0) {
            System.out.println("Split matrix should have cols as multiple of child cols");
            return null;
        } else {
            iterCol = (cols / newCols);
        }
        List<Integer[][]> result = new ArrayList<>();
        int startRow = 0;
        int startCol = 0;
        for (int k = 0; k < iterRow * iterCol; k++) {
            Integer[][] child = new Integer[newRows][newCols];
            for (int i = 0; i < newRows; i++) {
                for (int j = 0; j < newCols; j++) {
                    child[i][j] = matrix[startRow + i][startCol + j];
                }
            }
            result.add(child);
            if (startCol == cols - newCols) {
                startCol = 0;
                startRow += newRows;
            } else {
                startCol += newCols;
            }
        }
        return result;
    }

    private void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("-----");
    }

    public static void main(String[] args) {
        int M = 8; // Square matrix size (must be divisible by N)
        int N = 2; // Number of partitions (N must divide M)

        int[][] matrix = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = i + j; // Sample data
            }
        }

        MatrixParallelSplit workers = new MatrixParallelSplit();
        System.out.println("Original Matrix:");
        workers.printMatrix(matrix);

        workers.processForkJoinPool(N, M, matrix);
    }
}