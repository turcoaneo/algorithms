package org.toptal.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class KruskalSortedListMST {
    @TrackExecutionTime
    public List<Edge> kruskalMST(List<Edge> edges) {
        edges.sort(Comparator.comparingInt(e -> e.weight)); // Sort edges by weight

        Set<String> nodesInMST = new HashSet<>();
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            // Include edge only if it connects new nodes
            if (!nodesInMST.contains(edge.nodeA) || !nodesInMST.contains(edge.nodeB)) {
                mst.add(edge);
                nodesInMST.add(edge.nodeA);
                nodesInMST.add(edge.nodeB);

                // Stop early once MST has enough edges (V-1 edges)
                if (mst.size() == edges.size() - 1) break;
            }
        }

        // Output the final MST edges
        System.out.println("Minimum Spanning Tree:");
        for (Edge e : mst) {
            System.out.println("(" + e.nodeA + ", " + e.nodeB + ", " + e.weight + ")");
        }
        return mst;
    }

    public static void main(String[] args) {
        // Using List<Edge> instead of Map to ensure sorting by weight directly
        List<Edge> edges = getEdgeList();

        new KruskalSortedListMST().kruskalMST(edges);
    }

    public static List<Edge> getEdgeList() {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 3));
        edges.add(new Edge("A", "C", 1));
        edges.add(new Edge("B", "C", 3));
        edges.add(new Edge("B", "D", 4));
        edges.add(new Edge("C", "D", 2));
        return edges;
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
}