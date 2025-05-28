package org.algorithms.test.copilot.leet.hard.trap;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Component("trapRainWater3D")
public class Solution3D {
    @TrackExecutionTime
    public int trapRainWater(int[][] heightMap) {
        //noinspection DuplicatedCode
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;

        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Cell> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.height));

        // Step 1: Add boundary cells to the min-heap
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    minHeap.offer(new Cell(i, j, heightMap[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        int trappedWater = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // Step 2: Process cells using min-heap priority queue
        while (!minHeap.isEmpty()) {
            Cell cell = minHeap.poll();

            for (int[] dir : directions) {
                int nx = cell.x + dir[0], ny = cell.y + dir[1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    trappedWater += Math.max(0, cell.height - heightMap[nx][ny]);
                    minHeap.offer(new Cell(nx, ny, Math.max(heightMap[nx][ny], cell.height)));
                    visited[nx][ny] = true;
                }
            }
        }

        return trappedWater;
    }

    static class Cell {
        int x, y, height;

        public Cell(int x, int y, int height) {
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        Solution3D solver = new Solution3D();
        int[][] heightMap = {
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };
        System.out.println(solver.trapRainWater(heightMap)); // Expected output: 4

        int[][] heightMap2 = {
                {3, 3, 3, 3, 3},
                {3, 2, 2, 2, 3},
                {3, 2, 1, 2, 3},
                {3, 2, 2, 2, 3},
                {3, 3, 3, 3, 3}
        };
        System.out.println(solver.trapRainWater(heightMap2)); // Expected output: 10
    }
}