package org.algorithms.test.copilot.leetcode.hard.number;

import org.algorithms.test.copilot.leet.hard.number.SolutionRegex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeValidNumberTests {
    @Autowired
    SolutionRegex solutionRegex;
    @Autowired
    Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(Arrays.asList("2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"), true),
                Arguments.of(Arrays.asList("abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"), false)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(List<String> inputs, boolean expected) {
        for (String input : inputs) {
            boolean actual = solutionRegex.isNumber(input);
            Assertions.assertEquals(actual, expected);
            boolean solutionNumber = solution.isNumber(input);
            Assertions.assertEquals(solutionNumber, expected);
        }
    }
}