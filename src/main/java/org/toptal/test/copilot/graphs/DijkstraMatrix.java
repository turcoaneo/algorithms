package org.toptal.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class DijkstraMatrix {
    static final int INF = Integer.MAX_VALUE;

    @TrackExecutionTime
    public int[] dijkstra(int[][] graph, int source) {
        int graphLength = graph.length;
        int[] dist = new int[graphLength]; // Shortest distances
        boolean[] visited = new boolean[graphLength]; // Track visited nodes

        Arrays.fill(dist, INF); // Initialize distances to infinity
        dist[source] = 0; // Distance to source is zero

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> dist[node]));// comp for INF
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll(); // Get the node with the smallest known distance
            if (visited[u]) continue;
            visited[u] = true;

            // Relax all edges from node u
            for (int v = 0; v < graphLength; v++) {
                if (graph[u][v] != INF && !visited[v]) { // Check valid edge
                    int newDist = dist[u] + graph[u][v];
                    if (newDist < dist[v]) {
                        dist[v] = newDist; // Update shortest path
                        pq.add(v); // Add updated node to PQ
                    }
                }
            }
        }

        // Print shortest distances
        printSolution(dist, source);
        return dist;
    }

    private void printSolution(int[] dist, int source) {
        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("To node " + i + " → " + (dist[i] == INF ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) {
        // Example graph (Adjacency Matrix representation)
        int[][] graph = RoyFloyd.getRoyInput();

        new DijkstraMatrix().dijkstra(graph, 0); // Find shortest paths from node 0
    }
}