package org.algorithms.test.copilot.dp;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.Arrays;
import java.util.List;

@Component
public class GreedySurvivalKnapsack {

    @TrackExecutionTime
    public double knapsack(List<KnapsackItem> items, double capacity) {
        double remainingCapacity = capacity;
        double totalValue = 0;

        // Step 1: Pack mandatory items first
        System.out.println("Packing Mandatory KnapsackItems First:");
        for (KnapsackItem item : items) {
            int takenPartitions = 0;
            if (item.minPartitions > 0) {
                double takenWeight = item.weight / item.partitions;
                double takenValue = item.value / item.partitions;
                while (takenPartitions < item.minPartitions && item.remainingPartitions > 0
                        && remainingCapacity - takenWeight >= 0) {
                    remainingCapacity -= takenWeight;
                    totalValue += takenValue;
                    item.remainingPartitions -= 1;
                    takenPartitions += 1;
                    print(item, takenWeight, takenValue, remainingCapacity);
                }
            }
        }

        System.out.println("Partial Survival Value: " + Math.round(totalValue));

        // Step 2: Sort remaining items by value density
        items.sort((a, b) -> Double.compare(b.value / b.weight, a.value / a.weight));

        // Step 3: Greedy partition filling
        System.out.println("Filling Remaining Capacity Using Greedy Strategy:");
        for (KnapsackItem item : items) {
            double takenWeight = item.weight / item.partitions;
            double takenValue = item.value / item.partitions;
            while (item.remainingPartitions > 0 && remainingCapacity - takenWeight >= 0) {
                remainingCapacity -= takenWeight;
                totalValue += takenValue;
                item.remainingPartitions -= 1;
                print(item, takenWeight, takenValue, remainingCapacity);
            }
        }

        // Print Final Summary
        System.out.println("Final Survival Value: " + Math.round(totalValue));
        return totalValue;
    }

    private void print(KnapsackItem item, double takenWeight, double takenValue, double remainingCapacity) {
        System.out.println("✔ " + item.name + ". Remaining partitions: " + item.remainingPartitions + "." +
                ". takenWeight: " + takenWeight + "." + ". takenValue: " + takenValue +
                ". Remaining " + "total capacity: " + remainingCapacity + "kg.");
    }

    public static void main(String[] args) {
        List<KnapsackItem> items = getKnapsackItems();

        double capacity = 40; // Adjusted weight limit
        new GreedySurvivalKnapsack().knapsack(items, capacity); // Execute refined greedy selection
    }

    public static List<KnapsackItem> getKnapsackItems() {
        return Arrays.asList(
                new KnapsackItem("Tent", 20, 30, 1, 1),        // Must be taken whole
                new KnapsackItem("GPS", 6, 10, 2, 1),         // At least half must be taken
                new KnapsackItem("Emergency Food", 10, 24, 5, 2),  // At least 40% must be packed
                new KnapsackItem("Satellite Phone", 2, 6, 2, 1),  // At least half must be taken
                new KnapsackItem("Heating Pads", 6, 12, 3, 0), // Optional
                new KnapsackItem("Medical Kit", 8, 16, 2, 0)   // Optional
        );
    }
}