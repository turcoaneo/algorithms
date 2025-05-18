package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.graphs.*;

import java.util.*;


@SpringBootTest
public class GraphTests {
    @Autowired
    private KruskalSortedListMST kruskalSortedListMST;
    @Autowired
    private BFSMatrix bfsMatrix;
    @Autowired
    private BFSList bfsList;
    @Autowired
    private DFSList dfsList;
    @Autowired
    private DFSMatrix dfsMatrix;
    @Autowired
    private RoyFloyd royFloyd;
    @Autowired
    private DijkstraMatrix dijkstraMatrix;
    @Autowired
    private DijkstraRemastered dijkstraRemastered;


    @Test
    void testDijkstraRemastered() {
        Map<Integer, EdgeResult> result = dijkstraRemastered.dijkstra(DijkstraRemastered.getDijkstraMap(), 0);
        Map<Integer, EdgeResult> expected = new HashMap<>() {{
            put(1, new EdgeResult(3, "[0, 2, 1]"));
            put(2, new EdgeResult(1, "[0, 2]"));
            put(3, new EdgeResult(4, "[0, 2, 1, 3]"));
        }};
        Assertions.assertEquals(expected, result);
    }

    /*
    (A, B, 3), (A, C, NONE), (A, D, 5)
    (B, A, 2), (B, C, NONE), (B, D, 4)
    (C, A, NONE), (C, B, 1), (C, D, NONE)
    (D, A, NONE), (D, B, NONE), (D, C, 2)
    */

    @Test
    void testDijkstra() {
        int[] result = dijkstraMatrix.dijkstra(RoyFloyd.getRoyInput(), 0);
        int[] expected = new int[]{0, 3, 7, 5};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    void testFloyd() {
        int[][] result = royFloyd.floydWarshall(RoyFloyd.getRoyInput());
        int[][] expected = new int[][]{
                {0, 3, 7, 5},
                {2, 0, 6, 4},
                {3, 1, 0, 5},
                {5, 3, 2, 0}
        };
        for (int i = 0; i < result.length; i++) {
            Assertions.assertArrayEquals(expected[i], result[i]);
        }
    }

   /*
      A
    /   \
   3     1
   B ——  C
    \   /
     2 4
      D
      */

    @Test
    void testKruskal() {
        List<Edge> result = kruskalSortedListMST.kruskalMST(KruskalSortedListMST.getEdgeList());
        List<Edge> expected = new ArrayList<>();
        expected.add(new Edge("A", "C", 1));
        expected.add(new Edge("C", "D", 2));
        expected.add(new Edge("A", "B", 3));
        Assertions.assertIterableEquals(expected, result);
        Assertions.assertTrue(result.containsAll(expected));
    }



    /*1
     / \
    2   3
   / \   \
  4   5   6*/

    @Test
    void testBfs() {
        List<Integer> result = bfsMatrix.bfs(BFSMatrix.getGraph(), 0);// Start from node 0
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testListBfs() {
        List<Integer> result = bfsList.bfs(BFSList.getListGraph(), 0);// Start from node 0
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testListDfs() {
        List<Integer> result = dfsList.dfs(BFSList.getListGraph(), 0);// Start from node 0
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 3, 4, 2, 5));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testDfs() {
        List<Integer> result = dfsMatrix.dfs(BFSMatrix.getGraph(), 0);// Start from node 0
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 3, 4, 2, 5));
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testOptimizedDfs() {
        List<Integer> result = dfsMatrix.optimizedDFS(BFSMatrix.getGraph(), 0);// Start from node 0
        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 6, 2, 5, 4));
        Assertions.assertEquals(expected, result);
    }
}