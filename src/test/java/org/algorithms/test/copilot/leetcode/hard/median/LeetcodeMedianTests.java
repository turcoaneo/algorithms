package org.algorithms.test.copilot.leetcode.hard.median;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeMedianTests {
    @Autowired
    org.algorithms.test.copilot.leet.hard.median.Solution solution;

    static Stream<Arguments> getMedianArgs() {
        return Stream.of(
                Arguments.of(new int[]{1, 3}, new int[]{2}, 2.0D),
                Arguments.of(new int[]{1, 2}, new int[]{3, 4}, 2.5D)
        );
    }

    @ParameterizedTest
    @MethodSource("getMedianArgs")
    void testGetPerm(int[] nums1, int[] nums2, double expected) {
        double actual = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
}