package org.algorithms.test.copilot.leetcode.hard.parentheses;

import org.algorithms.test.copilot.leet.hard.parentheses.Solution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;


@SpringBootTest
public class LeetcodeParenthesesTests {
    @Autowired
    Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of("(()", 2),
                Arguments.of(")()())", 4),
                Arguments.of("", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(String input, int expected) {
        int actual = solution.longestValidParentheses(input);
        Assertions.assertEquals(actual, expected);
    }
}