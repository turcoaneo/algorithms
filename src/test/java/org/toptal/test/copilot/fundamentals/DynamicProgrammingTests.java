package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.dp.*;
import org.toptal.test.copilot.dp.tsp.SimulatedAnnealingTSP;
import org.toptal.test.copilot.dp.tsp.TSPBranchBound;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class DynamicProgrammingTests {

    @Autowired
    private GreedySurvivalKnapsack greedySurvivalKnapsack;

    @Autowired
    private SparseLCS sparseLCS;

    @Autowired
    private ClassicalLCS classicalLCS;

    @Autowired
    private CoinChange coinChange;

    @Autowired
    private WaysToMakeChange waysToMakeChange;


    @Test
    void testWaysToMakeChange() {
        int counter = waysToMakeChange.countWaysToMakeChange(10, new int[]{1, 3, 4});
        Assertions.assertEquals(8, counter);

        List<List<Integer>> combinations = waysToMakeChange.findCombinations(10, new int[]{1, 3, 4});
        Assertions.assertTrue(combinations.stream().anyMatch(c -> c.equals(Arrays.asList(3, 3, 4))));
    }


    @Test
    void testCoinChange() {
        List<Integer> coinsUsed = coinChange.getCoinsUsed(10, new int[]{1, 3, 4});
        Assertions.assertEquals(Arrays.asList(3, 3, 4), coinsUsed);
    }


    @Test
    void testClassicalLCS() {
        String result = classicalLCS.lcsClassical("GENERATION", "RECREATION");
        Assertions.assertEquals("E" + "RATION", result);
    }


    @Test
    void testSparseLCS() {
        double result = sparseLCS.lcsSparseDP("AIR", "LAIR");
        Assertions.assertEquals(3, result);
    }


    @Test
    void testKnapsack() {
        double result = greedySurvivalKnapsack.knapsack(GreedySurvivalKnapsack.getKnapsackItems(), 40);
        Assertions.assertEquals(73, Math.round(result));
    }
}