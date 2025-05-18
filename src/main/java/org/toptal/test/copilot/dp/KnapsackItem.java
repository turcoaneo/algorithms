package org.toptal.test.copilot.dp;

public class KnapsackItem {
    String name;
    double weight, value, partitions, minPartitions, remainingPartitions;

    KnapsackItem(String name, int w, int v, int p, int minP) {
        this.name = name;
        this.weight = w;
        this.value = v;
        this.partitions = p;
        this.minPartitions = minP;
        this.remainingPartitions = p; // Tracks how many partitions are still available
    }
}