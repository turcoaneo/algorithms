package org.toptal.test.copilot.graphs;


import java.util.Objects;

public class Edge{
    String nodeA, nodeB;
    int weight;

    public Edge(String a, String b, int w) {
        this.nodeA = a;
        this.nodeB = b;
        this.weight = w;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Same reference, objects are equal
        if (!(obj instanceof Edge other)) return false; // Different type, not equal

        return Objects.equals(nodeA, other.nodeA) &&
                Objects.equals(nodeB, other.nodeB) &&
                weight == other.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeA, nodeB, weight);
    }

}
