package org.algorithms.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class DijkstraRemastered {

    @TrackExecutionTime
    public Map<Integer, EdgeResult> dijkstra(Map<Integer, List<Cost>> graph, int source) {
        int V = graph.size();
        int[] dist = new int[V];
        int[] prev = new int[V]; // To reconstruct paths
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[source] = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(node -> dist[node]));
        pq.add(source);

        while (!pq.isEmpty()) {
            int u = pq.poll();

            for (Cost neighbor : graph.getOrDefault(u, Collections.emptyList())) {
                int v = neighbor.target;
                int newDist = dist[u] + neighbor.weight;

                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    prev[v] = u; // Store previous node for path reconstruction
                    pq.remove(v); // Ensure no duplicates before reinserting
                    pq.add(v);
                }
            }
        }

        return reconstructPaths(prev, dist);
    }

    private Map<Integer, EdgeResult> reconstructPaths(int[] prev, int[] dist) {
        Map<Integer, EdgeResult> paths = new HashMap<>();
        for (int i = 0; i < dist.length; i++) {
            if (prev[i] != -1) {
                String path = tracePath(prev, i);
                paths.put(i, new EdgeResult(dist[i], path));
                System.out.println("Path to " + i + ": " + path + " (Cost: " + dist[i] + ")");
            }
        }
        return paths;
    }

    private String tracePath(int[] prev, int node) {
        List<Integer> path = new ArrayList<>();
        while (node != -1) {
            path.add(node);
            node = prev[node];
        }
        Collections.reverse(path);
        return path.toString();
    }

    public static void main(String[] args) {
        Map<Integer, List<Cost>> graph = getDijkstraMap();

        System.out.println("Dijkstra's Shortest Path Results:");
        new DijkstraRemastered().dijkstra(graph, 0);
    }

    public static Map<Integer, List<Cost>> getDijkstraMap() {
        Map<Integer, List<Cost>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new Cost(1, 4), new Cost(2, 1)));
        graph.put(1, List.of(new Cost(3, 1)));
        graph.put(2, Arrays.asList(new Cost(1, 2), new Cost(3, 5)));
        graph.put(3, Collections.emptyList());
        return graph;
    }
}