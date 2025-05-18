package org.algorithms.test.copilot.fundamentals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.algorithms.test.copilot.dp.tsp.GeneticAlgorithmTSP;
import org.algorithms.test.copilot.dp.tsp.SimulatedAnnealingTSP;
import org.algorithms.test.copilot.dp.tsp.TSPBranchBound;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class ParametrizedTSPTest {
    @Autowired
    private SimulatedAnnealingTSP simulatedAnnealingTSP;

    @Autowired
    private TSPBranchBound tspBranchBound;

    @Autowired
    private GeneticAlgorithmTSP geneticAlgorithmTSP;

    static Integer[][] mediumMatrix = {
            { -1,  6,  5,  4,  8,  7,  3,  9 },
            {  7, -1,  2,  6,  5,  8,  4,  7 },
            {  5,  3, -1,  9,  2,  4,  6,  5 },
            {  4,  6,  7, -1,  3,  5,  8,  6 },
            {  8,  5,  2,  3, -1,  7,  9,  4 },
            {  7,  8,  4,  5,  6, -1,  2,  3 },
            {  3,  4,  6,  8,  9,  2, -1,  5 },
            {  9,  7,  5,  6,  4,  3,  5, -1 }
    };
    static Integer[][] smallMatrix = {
            { -1,  6,  5,  4 },
            {  7, -1,  3,  8 },
            {  4,  9, -1,  6 },
            {  5,  2,  7, -1 }
    };

    static Stream<Arguments> matrixProvider() {
        return Stream.of(
                Arguments.of(smallMatrix, 20, 0.995, Arrays.asList(0, 3, 1, 2), 13),
                Arguments.of(mediumMatrix, 100, 0.995, Arrays.asList(0, 3, 1, 2, 4, 7, 5, 6), 26)
        );
    }

    @ParameterizedTest
    @MethodSource("matrixProvider")
    void testMatrixMultiplication(Integer[][] matrix, double temperature, double coolingRate, List<Integer> path,
                                  int cost) {
        tspBranchBound.setBranchBound(matrix);
        List<Integer> solveBB = tspBranchBound.solve();
        tspBranchBound.printSolution();
        Assertions.assertEquals(tspBranchBound.getMinCost(), cost);
        Assertions.assertEquals(solveBB, path);

        double thresholdForFairResult = 0.4;
        double acceptedCostDiff = thresholdForFairResult * cost;

        simulatedAnnealingTSP.simulatedAnnealingTSP(matrix, temperature, coolingRate, true);
        List<Integer> solveSA = simulatedAnnealingTSP.solve();
        System.out.println("Approximate solution: " + solveSA);
        int costSA = simulatedAnnealingTSP.calculateCost(solveSA);
        System.out.println("Estimated cost: " + costSA);
        Assertions.assertTrue(costSA - cost < acceptedCostDiff);

        geneticAlgorithmTSP.setGeneticAlgorithmTSP(matrix, 100);
        List<Integer> bestPath = geneticAlgorithmTSP.solve();
        System.out.println("Optimal GA solution: " + bestPath);
        int costGA = geneticAlgorithmTSP.calculateCost(bestPath);
        System.out.println("Estimated cost: " + costGA);
        Assertions.assertTrue(costGA - cost < acceptedCostDiff);

        System.out.println();

    }


    @Test
    void testTravelingSalesman() {
        simulatedAnnealingTSP.simulatedAnnealingTSP(smallMatrix, 6, 0.995, false);
        List<Integer> solveSA = simulatedAnnealingTSP.solve();
        System.out.println("Approximate solution: " + solveSA);
        System.out.println("Estimated cost: " + simulatedAnnealingTSP.calculateCost(solveSA));

        tspBranchBound.setBranchBound(smallMatrix);
        List<Integer> solveBB = tspBranchBound.solve();
        tspBranchBound.printSolution();
        Assertions.assertEquals(solveSA, solveBB);
    }
}