package org.algorithms.test.copilot.leetcode.medium.ugly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeUglyNumber2Tests {
    @Autowired
    org.algorithms.test.copilot.leet.hard.ugly.Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(10, 12),
                Arguments.of(1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(int n, int expected) {
        int actual = solution.nthUglyNumber(n);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
}