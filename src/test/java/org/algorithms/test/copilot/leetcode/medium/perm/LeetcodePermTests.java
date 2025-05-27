package org.algorithms.test.copilot.leetcode.medium.perm;

import org.algorithms.test.copilot.leet.hard.perm.sequence.Solution;
import org.algorithms.test.copilot.leet.medium.perm.unique.HeapsIterative;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


@SpringBootTest
public class LeetcodePermTests {
    @Autowired
    org.algorithms.test.copilot.leet.medium.perm.unique.Solution hardUniquePerm;
    @Autowired
    HeapsIterative heapsIterative;
    @Autowired
    org.algorithms.test.copilot.leet.medium.perm.next.Solution nextPerm;
    @Autowired
    Solution sequencePerm;

    static Stream<Arguments> getPermProvider() {
        return Stream.of(
                Arguments.of(3, 3, "213"),
                Arguments.of(3, 1, "123"),
                Arguments.of(4, 9, "2314"),
                Arguments.of(2, 1, "12"),
                Arguments.of(9, 4, "123456897"),
                Arguments.of(9, 2, "123456798")
        );
    }

    @ParameterizedTest
    @MethodSource("getPermProvider")
    void testGetPerm(int n, int k, String expected) {
        String actual = sequencePerm.getPermutation(n, k);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> nextPermProvider() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, new int[]{1, 3, 2}),
                Arguments.of(new int[]{3, 2, 1}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{1, 1, 5}, new int[]{1, 5, 1})
        );
    }

    @ParameterizedTest
    @MethodSource("nextPermProvider")
    void testNextPerm(int[] actual, int[] expected) {
        nextPerm.nextPermutation(actual);
        System.out.println(Arrays.toString(actual));
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    void testPerm() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> expected = hardUniquePerm.permuteUnique(nums);
        System.out.println(expected);
        List<List<Integer>> actual = heapsIterative.permuteUnique(nums);
        Assertions.assertTrue(expected.stream().anyMatch(actual::contains));
    }

    @Test
    void testPerm1() {
        int[] nums = {1, 1, 2};
        List<List<Integer>> expected =
                Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1));
        List<List<Integer>> actual = hardUniquePerm.permuteUnique(nums);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPerm2() {
        int[] nums = {1, 2, 3};
        //[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
        List<List<Integer>> expected =
                Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
                        Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1));
        List<List<Integer>> actual = hardUniquePerm.permuteUnique(nums);
        System.out.println(actual);
        Assertions.assertEquals(expected, actual);
    }
}