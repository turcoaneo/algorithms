package org.algorithms.test.copilot.leetcode.hard.justify;

import org.algorithms.test.copilot.leet.hard.justify.Solution;
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
public class LeetcodeJustifyTests {
    @Autowired
    Solution solution;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16,
                        Arrays.asList("This    is    an",
                        "example  of text",
                        "justification.  ")),
                Arguments.of(new String[]{"What","must","be","acknowledgment","shall","be"}, 16,
                        Arrays.asList("What   must   be",
                                "acknowledgment  ",
                                "shall be        ")),
                Arguments.of(new String[]{"Science","is","what","we","understand","well","enough","to","explain","to"
                                ,"a","computer.","Art","is","everything","else","we","do"}, 20,
                        Arrays.asList("Science  is  what we",
                                "understand      well",
                                "enough to explain to",
                                "a  computer.  Art is",
                                "everything  else  we",
                                "do                  "))
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(String[] input, int maxWidth, List<String> expected) {
        List<String> actual = solution.fullJustify(input, maxWidth);
        Assertions.assertEquals(actual, expected);
    }
}