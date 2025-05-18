package org.toptal.test.copilot.dp.tsp;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class TSPBranchBound {
    private int N;
    private Integer[][] costMatrix;

    public int getMinCost() {
        return minCost;
    }

    private int minCost = Integer.MAX_VALUE;
    private List<Integer> bestPath = new ArrayList<>();


    public void setBranchBound(Integer[][] costMatrix) {
        this.minCost = Integer.MAX_VALUE;
        this.bestPath = new ArrayList<>();
        this.N = costMatrix.length;
        this.costMatrix = costMatrix;
    }

    @TrackExecutionTime
    public List<Integer> solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.bound));
        pq.add(new Node(new ArrayList<>(List.of(0)), 0, getLowerBound(new ArrayList<>(List.of(0)))));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.path.size() == N) {
                int totalCost = current.cost + costMatrix[current.path.get(N - 1)][0]; // Return to start
                if (totalCost < minCost) {
                    minCost = totalCost;
                    bestPath = new ArrayList<>(current.path);
//                    bestPath.add(0); // Complete cycle
                }
            } else {
                for (int i = 1; i < N; i++) {
                    if (!current.path.contains(i)) {
                        List<Integer> newPath = new ArrayList<>(current.path);
                        newPath.add(i);
                        int newCost = current.cost + costMatrix[current.path.get(current.path.size() - 1)][i];
                        int newBound = getLowerBound(newPath);

                        if (newBound < minCost) { // Branch pruning
                            pq.add(new Node(newPath, newCost, newBound));
                        }
                    }
                }
            }
        }
        return bestPath;
    }

    private int getLowerBound(List<Integer> path) {
        int bound = 0;
        Set<Integer> visited = new HashSet<>(path);

        for (int i = 0; i < N; i++) {
            if (!visited.contains(i)) {
                int minEdge = Integer.MAX_VALUE;
                for (int j = 0; j < N; j++) {
                    if (i != j) {
                        minEdge = Math.min(minEdge, costMatrix[i][j]);
                    }
                }
                bound += minEdge;
            }
        }

        return bound + (path.size() > 1 ? costMatrix[path.get(path.size() - 2)][path.get(path.size() - 1)] : 0);
    }

    public void printSolution() {
        System.out.println("Optimal tour: " + bestPath);
        System.out.println("Minimum cost: " + minCost);
    }

    private static class Node {
        List<Integer> path;
        int cost;
        int bound;

        public Node(List<Integer> path, int cost, int bound) {
            this.path = path;
            this.cost = cost;
            this.bound = bound;
        }
    }
}
