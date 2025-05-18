package org.algorithms.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class BFSMatrix {

    @TrackExecutionTime
    public List<Integer> bfs(int[][] graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        int n = graph.length; // Total nodes
        boolean[] visited = new boolean[n]; // Track visited nodes
        Queue<Integer> queue = new LinkedList<>();

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            System.out.print(node + " "); // Visit node

            // Check all adjacent nodes
            for (int i = 0; i < n; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] graph = getGraph();

        System.out.println("BFS Traversal:");
        new BFSMatrix().bfs(graph, 0); // Start from node 0
    }

    public static int[][] getGraph() {
        return new int[][]{
                {0, 1, 1, 0, 0, 0}, // Node 0
                {1, 0, 0, 1, 1, 0}, // Node 1
                {1, 0, 0, 0, 0, 1}, // Node 2
                {0, 1, 0, 0, 0, 0}, // Node 3
                {0, 1, 0, 0, 0, 0}, // Node 4
                {0, 0, 1, 0, 0, 0}  // Node 5
        };
    }
    /*1
     / \
    2   3
   / \   \
  4   5   6*/
}