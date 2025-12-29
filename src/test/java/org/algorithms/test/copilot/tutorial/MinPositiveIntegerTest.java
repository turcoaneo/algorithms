package org.algorithms.test.copilot.tutorial;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class MinPositiveIntegerTest {
    @Autowired
    private MinPositiveInteger minPositiveInteger;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, 4),
                Arguments.of(new int[]{-1, -3}, 1),
                Arguments.of(new int[]{-1, 1}, -1),
                Arguments.of(new int[]{-1, 2}, 1),
                Arguments.of(new int[]{-1, 3}, 1),
                Arguments.of(new int[]{-1}, -1),
                Arguments.of(new int[]{}, -1),
                Arguments.of(new int[]{-1, 1, 2, 3, 10}, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void testProcessLine(int[] inputLine, int actual) {
        int expected = minPositiveInteger.getMinPositiveInteger(inputLine);
        System.out.printf("Result = %d.", expected);
        System.out.println();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void testStandard() {
        MinPositiveInteger.Maxes maxes = minPositiveInteger.getMaxes(new int[]{1, 2, 3});
        Assertions.assertEquals(2, maxes.prev());
        Assertions.assertEquals(3, maxes.upper());
    }

    @Test
    void testMinPositiveInt() {
        int result = minPositiveInteger.getMinPositiveInteger(new int[]{-2, -1, 2, -5, 4, 6, 3, 1});
        System.out.printf("Result = %d.", result);
        Assertions.assertEquals(5, result);
    }
}