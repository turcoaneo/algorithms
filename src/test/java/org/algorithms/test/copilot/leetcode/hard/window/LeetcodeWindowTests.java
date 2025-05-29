package org.algorithms.test.copilot.leetcode.hard.window;

import org.algorithms.test.copilot.leet.hard.window.Solution;
import org.algorithms.test.copilot.leet.hard.window.SolutionMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeWindowTests {
    @Autowired
    SolutionMap solutionMap;
    @Autowired
    Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("ADOBECODEBANC", "ABC", "BANC"),
                Arguments.of("aa", "a", "a"),
                Arguments.of("a", "a", "a"),
                Arguments.of("a", "aa", "")
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(String s, String t, String expected) {
        String actual = solutionMap.minWindow(s, t);
        Assertions.assertEquals(expected, actual);
        String optimized = solution.minWindow(s, t);
        Assertions.assertEquals(expected, optimized);
    }
}