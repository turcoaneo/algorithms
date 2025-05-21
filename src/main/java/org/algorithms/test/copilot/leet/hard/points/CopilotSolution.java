package org.algorithms.test.copilot.leet.hard.points;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class CopilotSolution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int[] result = new int[queries.length];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[][] visited = new boolean[m][n];

        // Sort queries and maintain original indices
        int[][] indexedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[0]));

        // BFS-like expansion using min-heap
        minHeap.add(new int[]{grid[0][0], 0, 0});
        visited[0][0] = true;
        int points = 0;

        for (int[] q : indexedQueries) {
            int queryValue = q[0], queryIndex = q[1];

            while (!minHeap.isEmpty() && minHeap.peek()[0] < queryValue) {
                int[] cell = minHeap.poll();
                points++;
                int row = cell[1], col = cell[2];

                // Expand in four directions
                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
                for (int[] dir : directions) {
                    int newRow = row + dir[0], newCol = col + dir[1];
                    if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                        visited[newRow][newCol] = true;
                        minHeap.add(new int[]{grid[newRow][newCol], newRow, newCol});
                    }
                }
            }

            result[queryIndex] = points;
        }

        return result;
    }

    public static void main(String[] args) {
        CopilotSolution sol = new CopilotSolution();
        int[][] grid1 = {{1,2,3},{2,5,7},{3,5,1}};
        int[] queries1 = {5,6,2};
        System.out.println(Arrays.toString(sol.maxPoints(grid1, queries1))); // Output: [5, 8, 1]

        int[][] grid2 = {{5,2,1},{1,1,2}};
        int[] queries2 = {3};
        System.out.println(Arrays.toString(sol.maxPoints(grid2, queries2))); // Output: [0]

        int[][] grid3 = {{1,2,2,2},{2,3,7,5},{5,3,3,3},{4,4,5,4}};
        int[] queries3 = {5};
        System.out.println(Arrays.toString(sol.maxPoints(grid3, queries3)));
    }
}