package org.algorithms.test.copilot.leetcode.range;

import org.algorithms.test.copilot.leet.range.ListSolution;
import org.algorithms.test.copilot.leet.range.PrioritySolution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeRangeTests {
    @Autowired
    PrioritySolution prioritySolution;
    @Autowired
    ListSolution listSolution;

    static Stream<Arguments> getRangeProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 4}, 4, 1, 5, 13),
                Arguments.of(new int[]{1, 2, 3, 4}, 4, 3, 4, 6),
                Arguments.of(new int[]{1, 2, 3, 4}, 4, 1, 10, 50)
        );
    }

    @ParameterizedTest
    @MethodSource("getRangeProvider")
    void testCompare(int[] nums, int n, int left, int right, int expected) {
        int output = listSolution.rangeSum(nums, n, left, right);
        Assertions.assertEquals(expected, output);
        int actual = prioritySolution.rangeSum(nums, n, left, right);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("getRangeProvider")
    void testSimple(int[] nums, int n, int left, int right, int expected) {
        int actual = listSolution.rangeSum(nums, n, left, right);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
}