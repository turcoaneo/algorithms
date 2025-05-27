package org.algorithms.test.copilot.leetcode.hard.words;

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
public class LeetcodeWordsConcatenationTests {
    @Autowired
    org.algorithms.test.copilot.leet.hard.words.Solution solution;

    static Stream<Arguments> getArgs() {


        return Stream.of(
                Arguments.of("barfoothefoobarman", new String[]{"foo", "bar"}, Arrays.asList(0, 9)),
                Arguments.of("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "word"}, List.of()),
                Arguments.of("barfoofoobarthefoobarman", new String[]{"bar","foo","the"}, Arrays.asList(6, 9, 12))
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void test(String s, String[] words, List<Integer> expected) {
        List<Integer> actual = solution.findSubstring(s, words);
        Assertions.assertEquals(actual, expected);
    }
}