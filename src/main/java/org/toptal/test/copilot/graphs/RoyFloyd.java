package org.toptal.test.copilot.graphs;

import org.springframework.stereotype.Component;

@Component
public class RoyFloyd {
    public static final int INF = 1_000_000_000; // Large value representing "infinity"

    public int[][] floydWarshall(int[][] graph) {
        int V = graph.length;
        int[][] dist = new int[V][V];

        // Initialize the distance matrix
        for (int i = 0; i < V; i++) {
            System.arraycopy(graph[i], 0, dist[i], 0, V);
        }

        // Core Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] < INF && dist[k][j] < INF) { // Prevent integer overflow
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // Print the shortest distances matrix
        printSolution(dist);
        return dist;
    }

    private void printSolution(int[][] dist) {
        System.out.println("Shortest distances between every pair of vertices:");
        for (int[] row : dist) {
            for (int value : row) {
                System.out.print(value == INF ? "INF " : value + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] graph = getRoyInput();

        new RoyFloyd().floydWarshall(graph);
    }

    public static int[][] getRoyInput() {
        return new int[][]{
                {0, 3, INF, 5},
                {2, 0, INF, 4},
                {INF, 1, 0, INF},
                {INF, INF, 2, 0}
        };
    }
    /*
    (A, B, 3), (A, C, NONE), (A, D, 5)
    (B, A, 2), (B, C, NONE), (B, D, 4)
    (C, A, NONE), (C, B, 1), (C, D, NONE)
    (D, A, NONE), (D, B, NONE), (D, C, 2)
    */
}