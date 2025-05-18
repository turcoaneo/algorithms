package org.algorithms.test.copilot.graphs;

import java.util.Objects;

public class EdgeResult {
    int distance;
    String path;

    public EdgeResult(int distance, String path) {
        this.distance = distance;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EdgeResult that = (EdgeResult) o;
        return distance == that.distance && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, path);
    }
}