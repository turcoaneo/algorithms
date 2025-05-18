package org.algorithms.test.copilot.dp.tsp;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component
public class GeneticAlgorithmTSP {
    private Integer[][] costMatrix;
    private int N;
    private int populationSize;
    private final double mutationRate = 0.05;
    private final int generations = 500;
    private final Random rand = new Random();

    public void setGeneticAlgorithmTSP(Integer[][] costMatrix, int populationSize) {
        this.costMatrix = costMatrix;
        this.N = costMatrix.length;
        this.populationSize = populationSize;
    }

    @TrackExecutionTime
    public List<Integer> solve() {
        List<List<Integer>> population = initializePopulation();
        for (int gen = 0; gen < generations; gen++) {
            List<List<Integer>> newPopulation = new ArrayList<>();

            for (int i = 0; i < populationSize / 2; i++) {
                List<Integer> parent1 = selectParent(population);
                List<Integer> parent2 = selectParent(population);

                List<Integer> offspring = crossover(parent1, parent2);
                if (rand.nextDouble() < mutationRate) {
                    mutate(offspring);
                }

                newPopulation.add(offspring);
            }

            population = newPopulation;
        }

        return findBestRoute(population);
    }

    private List<List<Integer>> initializePopulation() {
        List<List<Integer>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<Integer> path = new ArrayList<>(List.of(0)); // Start at city 0
            for (int j = 1; j < N; j++) {
                path.add(j);
            }
            Collections.shuffle(path.subList(1, N));
            population.add(path);
        }
        return population;
    }

    private List<Integer> selectParent(List<List<Integer>> population) {
//        return population.get(rand.nextInt(populationSize)); // Random selection for simplicity
        return population.get(rand.nextInt(population.size())); // Ensures valid index
    }

    private List<Integer> crossover(List<Integer> parent1, List<Integer> parent2) {
        int crossoverPoint = rand.nextInt(N - 1) + 1;
        List<Integer> offspring = new ArrayList<>(parent1.subList(0, crossoverPoint));

        for (Integer city : parent2) {
            if (!offspring.contains(city)) {
                offspring.add(city);
            }
        }

        return offspring;
    }

    private void mutate(List<Integer> path) {
        int i = rand.nextInt(N - 1) + 1;
        int j = rand.nextInt(N - 1) + 1;
        Collections.swap(path, i, j);
    }

    private List<Integer> findBestRoute(List<List<Integer>> population) {
        return Collections.min(population, Comparator.comparingInt(this::calculateCost));
    }

    public int calculateCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < N - 1; i++) {
            cost += costMatrix[path.get(i)][path.get(i + 1)];
        }
        cost += costMatrix[path.get(N - 1)][path.get(0)];
        return cost;
    }

    public static void main(String[] args) {
        Integer[][] costMatrix = {
                {-1, 6, 5, 4},
                {7, -1, 3, 8},
                {4, 9, -1, 6},
                {5, 2, 7, -1}
        };

        GeneticAlgorithmTSP gaTSP = new GeneticAlgorithmTSP();
        gaTSP.setGeneticAlgorithmTSP(costMatrix, 100);
        List<Integer> bestPath = gaTSP.solve();
        System.out.println("Optimal GA solution: " + bestPath);
        System.out.println("Estimated cost: " + gaTSP.calculateCost(bestPath));
    }
}