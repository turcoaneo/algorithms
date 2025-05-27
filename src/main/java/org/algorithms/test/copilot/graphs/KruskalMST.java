package org.algorithms.test.copilot.graphs;
import java.util.*;

class KruskalMST {

    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int node) {
            if (parent[node] != node) {
                parent[node] = find(parent[node]); // Path compression
            }
            return parent[node];
        }

        public boolean union(int u, int v) {
            int rootU = find(u), rootV = find(v);
            if (rootU == rootV) return false; // Cycle detected

            // Union by rank optimization
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }
    }

    public static List<EdgeCSV> kruskalMST(List<EdgeCSV> edges, int V) {
        edges.sort(Comparator.comparingInt(e -> e.weight)); // Sort edges by weight
        UnionFind uf = new UnionFind(V);
        List<EdgeCSV> mst = new ArrayList<>();

        for (EdgeCSV edge : edges) {
            if (uf.union(edge.src, edge.dest)) { // Only add if it connects new components
                mst.add(edge);
                if (mst.size() == V - 1) break; // Stop when MST is complete
            }
        }

        return mst;
    }

    public static void main(String[] args) {
        int V = 5;
        String filePath = "src/main/java/org/algorithms/test/copilot/graphs/edges.csv";
        List<EdgeCSV> edges = CSVReader.loadEdgesFromCSV(filePath);

        List<EdgeCSV> mst = kruskalMST(edges, V);
        System.out.println("Minimum Spanning Tree:");
        for (EdgeCSV e : mst) {
            System.out.println("(" + e.src + ", " + e.dest + ", " + e.weight + ")");
        }
    }
}