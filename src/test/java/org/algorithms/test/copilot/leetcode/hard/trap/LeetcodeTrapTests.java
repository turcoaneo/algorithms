package org.algorithms.test.copilot.leetcode.hard.trap;

import org.algorithms.test.copilot.leet.hard.trap.Solution;
import org.algorithms.test.copilot.leet.hard.trap.TrappingRainWaterII;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class LeetcodeTrapTests {
    @Autowired
    Solution solution;
    @Autowired
    TrappingRainWaterII trappingRainWater;

    int[][] heightMap2 = {
            {3, 3, 3, 3, 3},
            {3, 2, 2, 2, 3},
            {3, 2, 1, 2, 3},
            {3, 2, 2, 2, 3},
            {3, 3, 3, 3, 3}
    };

    @Test
    void test() {
        int actual = solution.trapRainWater(heightMap2);
        Assertions.assertEquals(10, actual);
    }

    @Test
    void testOptimized() {
        int actual = trappingRainWater.trapRainWater(heightMap2);
        Assertions.assertEquals(10, actual);
    }
}