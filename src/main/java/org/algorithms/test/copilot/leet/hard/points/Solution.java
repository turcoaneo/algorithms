package org.algorithms.test.copilot.leet.hard.points;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Arrays;

class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int[] result = new int[queries.length];

        // Store queries with their original indices
        int[][] indexedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            indexedQueries[i] = new int[]{queries[i], i};
        }

        // Sort queries by value
        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[0]));

        // Priority queue (min-heap) for BFS traversal
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        boolean[][] visited = new boolean[m][n];

        // Start BFS from top-left corner
        pq.offer(new int[]{0, 0, grid[0][0]});
        visited[0][0] = true;
        int points = 0;

        for (int[] q : indexedQueries) {
            int queryValue = q[0];
            int queryIndex = q[1];

            // Expand nodes in priority queue as long as values are below query threshold
            while (!pq.isEmpty() && pq.peek()[2] < queryValue) {
                int[] current = pq.poll();
                points++; // Gain points for traversable cells

                // Explore adjacent cells (Up, Down, Left, Right)
                int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
                for (int[] dir : directions) {
                    int x = current[0] + dir[0], y = current[1] + dir[1];

                    if (x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                        visited[x][y] = true;
                        pq.offer(new int[]{x, y, grid[x][y]});
                    }
                }
            }

            result[queryIndex] = points; // Store result at correct original index
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid3 = {{249472,735471,144880,992181,760916,920551,898524,37043,422852,194509,714395,325171},
                         {295872,922051,900801,634980,644237,912433,857189,98466,725226,984534,370121,399006},
                         {618420,573065,587011,298153,694872,12760,880413,593508,474772,291113,852444,77998},
                         {67650,426517,146447,190319,379151,184754,479219,106819,138473,865661,799297,228827},
                         {390392,789371,772048,730506,7144,862164,650590,21524,879440,396198,408897,851020},
                         {932044,662093,436861,246956,128943,167432,267483,148325,458128,418348,900594,831373},
                         {742255,795191,598857,441846,243888,777685,313717,560586,257220,488025,846817,554722},
                         {252507,621902,87704,599753,651175,305330,620166,631193,385405,183376,432598,706692},
                         {984416,996917,586571,324595,784565,300514,101313,685863,703194,729430,732044,349877},
                         {155629,290992,539879,173659,989930,373725,701670,992137,893024,455455,454886,559081},
                         {252809,641084,632837,764260,68790,732601,349257,208701,613650,429049,983008,76324},
                         {918085,126894,909148,194638,915416,225708,184408,462852,40392,964501,436864,785076},
                         {875475,442333,111818,494972,486734,901577,46210,326422,603800,176902,315208,225178},
                         {171174,458473,744971,872087,680060,95371,806370,322605,349331,736473,306720,556064},
                         {207705,587869,129465,543368,840821,977451,399877,486877,327390,8865,605705,481076}};
                         
        int[] queries3 = {690474,796832,913701,939418,46696,266869,150594,948153,718874};

        System.out.println(Arrays.toString(sol.maxPoints(grid3, queries3))); // Expected Output: [85,145,166,171,0,1,0,171,126]
    }
}