package org.algorithms.test.copilot.leetcode.squareful;

import org.algorithms.test.copilot.leet.squareful.Solution;
import org.algorithms.test.copilot.leet.squareful.SquarefulArrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@SpringBootTest
public class LeetcodeSquarefulTests {
    @Autowired
    Solution numberOfSquarefulArrays;
    @Autowired
    SquarefulArrays squarefulArrays;

    @Test
    void testDfsTwo() {
        int[] nodes = new int[]{2, 2};
        int actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(1, actual);

        nodes = new int[]{2};
        actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void testDfsFour() {
        int[] nodes = new int[]{2, 2, 14, 11};
        int[][] G = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {0, 0, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 2, neighbors);
        Assertions.assertNotEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 3, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(2, setList.size());
    }

    @Test
    void testDfsOne() {
        int[] nodes = new int[]{2, 2, 2, 2};
        int[][] G = new int[][]{
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 0, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 3, neighbors);
        Assertions.assertEquals(4, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(1, setList.size());
    }

    @Test
    void testDfsOne2() {
        int[] nodes = new int[]{1, 8, 17};
        int[][] G = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0},
        };
        List<Integer> neighbors;
        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 0, neighbors);
        Assertions.assertEquals(3, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 2, 1, neighbors);
        Assertions.assertEquals(1, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 1, 2, neighbors);
        Assertions.assertEquals(3, neighbors.size());

        neighbors = new ArrayList<>();
        squarefulArrays.traverseDFSByNode(G, 0, 1, neighbors);
        Assertions.assertEquals(1, neighbors.size());

        Set<List<Integer>> setList = squarefulArrays.traverseDFS(G, nodes);
        Assertions.assertEquals(2, setList.size());
    }

    @Test
    void testSquarefulDfs() {
        int[] nodes = new int[]{1, 17, 8};

        int actual = squarefulArrays.numSquarefulPerms(nodes);
        Assertions.assertEquals(2, actual);
    }

    @Test
    void testSquareful() {
        int result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{1, 17, 8});
        Assertions.assertEquals(2, result);

        result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{2, 2, 2});
        Assertions.assertEquals(1, result);

        result = numberOfSquarefulArrays.numSquarefulPerms(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        Assertions.assertEquals(0, result);
    }
}