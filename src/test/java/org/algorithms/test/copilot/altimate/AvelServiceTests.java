package org.algorithms.test.copilot.altimate;

import org.algorithms.test.copilot.avel.AvelService;
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
public class AvelServiceTests {
    @Autowired
    AvelService avelService;

    static Stream<Arguments> getArgs() {
        return Stream.of(
                Arguments.of(Arrays.asList(1, 3, 5), false),
                Arguments.of(Arrays.asList(2, 4, 6), true)
        );
    }

    @ParameterizedTest
    @MethodSource("getArgs")
    void testIsEven(List<Integer> inputs, boolean expected) {
        for (Integer input : inputs) {
            int actual = avelService.sampleMethod();
            Assertions.assertEquals(actual, 0);
            actual = avelService.sampleMethod(input);
            Assertions.assertEquals(actual % 2 == 0, !expected);
        }
    }
}