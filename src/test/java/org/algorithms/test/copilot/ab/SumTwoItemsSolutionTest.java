package org.algorithms.test.copilot.ab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class SumTwoItemsSolutionTest {
    @Autowired
    private SumTwoItemsSolution solution;

    static Stream<Arguments> inputTwoItemsSum() {
        return Stream.of(
                Arguments.of(List.of(-11, -7, -4, -3, 0, 1, 3, 6, 10, 11, 15), 11,
                        List.of(new SumTwoItemsSolution.Solution(-4, 15), new SumTwoItemsSolution.Solution(0, 11),
                                new SumTwoItemsSolution.Solution(1, 10))),
                Arguments.of(List.of(-1, 0, 1), 2, new ArrayList<>()),
                Arguments.of(List.of(-10, 0, 10), -10, List.of(new SumTwoItemsSolution.Solution(-10, 0))),
                Arguments.of(new ArrayList<>(), 0, new ArrayList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("inputTwoItemsSum")
    void testProcessLine(List<Integer> input, Integer sum, List<SumTwoItemsSolution.Solution> result) {
        List<SumTwoItemsSolution.Solution> bruteForce = solution.solveBruteForce(input, sum);
        List<SumTwoItemsSolution.Solution> tongs = solution.solveByTongs(input, sum);
        Assertions.assertEquals(bruteForce, result);
        Assertions.assertEquals(tongs, result);
    }
}