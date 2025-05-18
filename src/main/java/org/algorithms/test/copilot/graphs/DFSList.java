package org.algorithms.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class DFSList {

    @TrackExecutionTime
    public List<Integer> dfs(Map<Integer, List<Integer>> graph, int startNode) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);
                System.out.print(node + " "); // Visit node

                // Push adjacent nodes onto the stack (reverse order for correct traversal)
                List<Integer> neighbors = graph.getOrDefault(node, new ArrayList<>());
                Collections.reverse(neighbors); // Ensures order consistency
                for (int neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // Adjacency list representation
        Map<Integer, List<Integer>> graph = BFSList.getListGraph();

        System.out.println("DFS Traversal:");
        new DFSList().dfs(graph, 0); // Start from node 0
    }

    /*1
     / \
    2   3
   / \   \
  4   5   6*/
}