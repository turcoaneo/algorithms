package org.toptal.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Component
public class DFSMatrix {

    @TrackExecutionTime
    public List<Integer> dfs(int[][] graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        int n = graph.length; // Total nodes
        boolean[] visited = new boolean[n]; // Track visited nodes
        Stack<Integer> stack = new Stack<>();

        stack.push(startNode);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                result.add(node);
                System.out.print(node + " "); // Visit node

                // Push adjacent nodes onto the stack
                for (int i = n - 1; i >= 0; i--) {
                    if (graph[node][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return result;
    }

    @TrackExecutionTime
    public List<Integer> optimizedDFS(int[][] graph, int startNode) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                result.add(node + 1);
                System.out.print(node + 1 + " ");

                // Push unvisited adjacent nodes in reverse order (for consistent traversal)
                for (int i = 0; i < n; i++) {
                    if (graph[node][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 0, 0, 0}, // Node 0
                {1, 0, 0, 1, 1, 0}, // Node 1
                {1, 0, 0, 0, 0, 1}, // Node 2
                {0, 1, 0, 0, 0, 0}, // Node 3
                {0, 1, 0, 0, 0, 0}, // Node 4
                {0, 0, 1, 0, 0, 0}  // Node 5
        };

        System.out.println("DFS Traversal:");
        new DFSMatrix().dfs(graph, 0); // Start from node 0

        System.out.println();

        System.out.println("DFS optimized traversal:");
        new DFSMatrix().optimizedDFS(graph, 0); // Start from node 0
    }
    /*1
     / \
    2   3
   / \   \
  4   5   6*/
}