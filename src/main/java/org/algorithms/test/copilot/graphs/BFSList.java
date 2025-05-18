package org.algorithms.test.copilot.graphs;
import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class BFSList {

    @TrackExecutionTime
    public List<Integer> bfs(Map<Integer, List<Integer>> graph, int startNode) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);
            System.out.print(node + " "); // Visit node

            // Explore unvisited neighbors
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Adjacency list representation
        Map<Integer, List<Integer>> graph = getListGraph();

        System.out.println("BFS Traversal:");
        new BFSList().bfs(graph, 0); // Start from node 0
    }


    public static Map<Integer, List<Integer>> getListGraph() {
        Map<Integer, List<Integer>> listGraph = new HashMap<>();
        listGraph.put(0, Arrays.asList(1, 2));
        listGraph.put(1, Arrays.asList(0, 3, 4));
        listGraph.put(2, Arrays.asList(0, 5));
        listGraph.put(3, List.of(1));
        listGraph.put(4, List.of(1));
        listGraph.put(5, List.of(2));
        return listGraph;
    }
    /*1
     / \
    2   3
   / \   \
  4   5   6*/
}