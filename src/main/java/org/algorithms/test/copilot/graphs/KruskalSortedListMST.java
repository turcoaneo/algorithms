package org.algorithms.test.copilot.graphs;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class KruskalSortedListMST {
    @TrackExecutionTime
    public List<Edge> kruskalMST(List<Edge> Edges) {
        Edges.sort(Comparator.comparingInt(e -> e.weight)); // Sort edges by weight

        Set<String> nodesInMST = new HashSet<>();
        List<Edge> mst = new ArrayList<>();

        for (Edge Edge : Edges) {
            // Include edge only if it connects new nodes
            if (!nodesInMST.contains(Edge.nodeA) || !nodesInMST.contains(Edge.nodeB)) {
                mst.add(Edge);
                nodesInMST.add(Edge.nodeA);
                nodesInMST.add(Edge.nodeB);

                // Stop early once MST has enough edges (V-1 edges)
                if (mst.size() == Edges.size() - 1) break;
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
        List<Edge> Edges = getEdgeList();

        new KruskalSortedListMST().kruskalMST(Edges);
    }

    public static List<Edge> getEdgeList() {
        List<Edge> Edges = new ArrayList<>();
        Edges.add(new Edge("A", "B", 3));
        Edges.add(new Edge("A", "C", 1));
        Edges.add(new Edge("B", "C", 3));
        Edges.add(new Edge("B", "D", 4));
        Edges.add(new Edge("C", "D", 2));
        return Edges;
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