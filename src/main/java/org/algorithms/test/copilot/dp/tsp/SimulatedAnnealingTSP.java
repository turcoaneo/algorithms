package org.algorithms.test.copilot.dp.tsp;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimulatedAnnealingTSP {
    private Integer[][] costMatrix;
    private int N;
    private double temperature;
    private double coolingRate;
    private boolean heuristicStart;


    public void simulatedAnnealingTSP(Integer[][] costMatrix, double temperature, double coolingRate, boolean heuristicStart) {
        this.costMatrix = costMatrix;
        this.N = costMatrix.length;
        this.temperature = temperature;
        this.coolingRate = coolingRate;
        this.heuristicStart = heuristicStart;
    }

    @TrackExecutionTime
    public List<Integer> solve() {
        List<Integer> currentPath = generateInitialPath();
        if (heuristicStart) {
            currentPath = generateHeuristicInitialPath();
        }
        int currentCost = calculateCost(currentPath);

        while (temperature > 1) {
            List<Integer> newPath = generateNeighbor(currentPath);
//            if (heuristicStart) {
//                newPath = generateBestNeighbor(currentPath);
//            }
            int newCost = calculateCost(newPath);

            if (acceptanceProbability(currentCost, newCost, temperature) > Math.random()) {
                currentPath = new ArrayList<>(newPath);
                currentCost = newCost;
            }

            temperature *= coolingRate;
        }

        return currentPath;
    }

    private List<Integer> generateInitialPath() {
        List<Integer> path = new ArrayList<>(List.of(0));  // Start from city 0
        for (int i = 1; i < N; i++) {
            path.add(i);
        }
        Collections.shuffle(path.subList(1, N)); // Shuffle only cities 1, 2, 3
        return path;
    }

    private List<Integer> generateHeuristicInitialPath() {
        List<Integer> path = new ArrayList<>(List.of(0));  // Start from city 0
        Map<Integer, Integer> cities = new HashMap<>();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j != 0 && i != j) {
                    Integer currentCost = cities.getOrDefault(j, costMatrix[i][j]);
                    if (currentCost >= costMatrix[i][j]) {
                        cities.put(j, costMatrix[i][j]);
                    }
                }
            }
        }

        cities = cities.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


        path.addAll(new ArrayList<>(cities.keySet()));

        return path;
    }

    private List<Integer> generateNeighbor(List<Integer> path) {
        List<Integer> newPath = new ArrayList<>(path);
        int i = new Random().nextInt(N - 1) + 1; // Ensure index is 1–N (keeping 0 fixed)
        int j = new Random().nextInt(N - 1) + 1;
        Collections.swap(newPath, i, j);
        return newPath;
    }

    private List<Integer> generateBestNeighbor(List<Integer> path) {
        List<Integer> newPath = new ArrayList<>(path);
        Random rand = new Random();

        // First swap: Random exploration
        int i = rand.nextInt(N - 1) + 1; // Ensure index is 1–N (keeping 0 fixed)
        int j = rand.nextInt(N - 1) + 1;
        while (i == j) {  // Prevent swapping the same city
            j = rand.nextInt(N - 1) + 1;
        }
        Collections.swap(newPath, i, j);

        // Second swap: Improve worst-cost connection
        int worstCityIndex = findWorstConnectedCity(newPath);
        int bestSwapCandidate = findBestSwapCandidate(newPath, worstCityIndex);

        if (bestSwapCandidate != -1) {
            Collections.swap(newPath, worstCityIndex, bestSwapCandidate);
        }

        return newPath;
    }

    // Identify the city with the worst connection in the current tour
    private int findWorstConnectedCity(List<Integer> path) {
        int worstIndex = -1;
        int maxCost = Integer.MIN_VALUE;

        for (int i = 1; i < path.size() - 1; i++) { // Skip starting city
            int cost = costMatrix[path.get(i - 1)][path.get(i)] + costMatrix[path.get(i)][path.get(i + 1)];
            if (cost > maxCost) {
                maxCost = cost;
                worstIndex = i;
            }
        }

        return worstIndex;
    }

    // Identify a better candidate to swap the worst-connected city with
    private int findBestSwapCandidate(List<Integer> path, int worstIndex) {
        if (worstIndex == -1) return -1;

        int bestCandidate = -1;
        int minCost = Integer.MAX_VALUE;

        for (int i = 1; i < path.size(); i++) { // Ensure we don’t swap the fixed start city
            if (i != worstIndex) {
                int cost = costMatrix[path.get(i - 1)][path.get(i)] + costMatrix[path.get(i)][path.get((i + 1) % path.size())];
                if (cost < minCost) {
                    minCost = cost;
                    bestCandidate = i;
                }
            }
        }

        return bestCandidate;
    }

    public int calculateCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < N - 1; i++) {
            cost += costMatrix[path.get(i)][path.get(i + 1)];
        }
        cost += costMatrix[path.get(N - 1)][path.get(0)]; // Return to start
        return cost;
    }

    private double acceptanceProbability(int currentCost, int newCost, double temp) {
        if (newCost < currentCost) return 1.0;
        return Math.exp((currentCost - newCost) / temp);
    }

    public static void main(String[] args) {
        Integer[][] costMatrix = {
                {-1, 6, 5, 4},
                {7, -1, 3, 8},
                {4, 9, -1, 6},
                {5, 2, 7, -1}
        };

        SimulatedAnnealingTSP saTSP = new SimulatedAnnealingTSP();
        saTSP.simulatedAnnealingTSP(costMatrix, 4, 0.995, true);
        List<Integer> bestPath = saTSP.solve();
        System.out.println("Approximate solution: " + bestPath);
        System.out.println("Estimated cost: " + saTSP.calculateCost(bestPath));
    }
}
