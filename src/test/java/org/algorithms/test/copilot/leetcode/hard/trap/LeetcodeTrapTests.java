package org.algorithms.test.copilot.leetcode.hard.trap;

import org.algorithms.test.copilot.leet.hard.trap.Solution2D;
import org.algorithms.test.copilot.leet.hard.trap.Solution3D;
import org.algorithms.test.copilot.leet.hard.trap.TrappingRainWaterII;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeTrapTests {
    @Autowired
    Solution2D solution2D;
    @Autowired
    Solution3D solution3D;
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
        int actual = solution3D.trapRainWater(heightMap2);
        Assertions.assertEquals(10, actual);
    }

    @Test
    void testOptimized() {
        int actual = trappingRainWater.trapRainWater(heightMap2);
        Assertions.assertEquals(10, actual);
    }

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6),
                Arguments.of(new int[]{4,2,0,3,2,5}, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(int[] input, int expected) {
        int actual = solution2D.trap(input);
        Assertions.assertEquals(actual, expected);
    }
}