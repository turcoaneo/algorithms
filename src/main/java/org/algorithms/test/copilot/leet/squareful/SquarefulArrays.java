package org.algorithms.test.copilot.leet.squareful;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SquarefulArrays {
    int[][] graph;
    int n;

    @TrackExecutionTime
    public int numSquarefulPerms(int[] nums) {
        n = nums.length;
        if (n == 1) return 0;
        if (n == 2) return isPerfectSquare(nums[0] + nums[1]) ? 1 : 0;
        graph = new int[n][n];

        buildGraph(nums);

        Set<List<Integer>> sets = traverseDFS(graph, nums);
        return sets.size();
    }

    private void buildGraph(int[] nums) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && isPerfectSquare(nums[i] + nums[j])) {
                    graph[i][j] = 1;
                }
            }
        }
    }


    @TrackExecutionTime
    public Set<List<Integer>> traverseDFS(int[][] graph, int[] nodes) {
        int n = graph.length;
        Set<List<Integer>> setList = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    List<Integer> nodeList = new ArrayList<>();
                    traverseDFSByNode(graph, i, j, nodeList);
                    if (nodeList.size() == n) {
                        List<Integer> result = nodeList.stream().map(x -> nodes[x]).collect(Collectors.toList());
                        setList.add(result);
                    }
                }
            }
        }
        return setList;

    }

    @TrackExecutionTime
    public void traverseDFSByNode(int[][] graph, int row, int col, List<Integer> neighbors) {
        int n = graph.length;
        if (neighbors.size() == n) {
            return;
        }
        if (neighbors.isEmpty()) {
            neighbors.add(col);
            traverseDFSByNode(graph, col, row, neighbors);
        }
        int counter = 1;
//        neighbors.add(col);
        while (n > counter) {
            int i = (row + counter) % n;
            ++counter;
            if (graph[i][col] == 1 && neighbors.size() < n && !neighbors.contains(col)) {
                neighbors.add(col);
                if (neighbors.size() == n - 1 && !neighbors.contains(i)) {
                    neighbors.add(i);
                }
                traverseDFSByNode(graph, col, i, neighbors);
            }
        }
    }

    private boolean isPerfectSquare(int sum) {
        int root = (int) Math.sqrt(sum);
        return root * root == sum;
    }
}
