package org.algorithms.test.copilot.ab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class ABStringSolutionTest {
    @Autowired
    private ABStringSolution abStringSolution;
    @Autowired
    private BruteForceValidator bruteForceValidator;
    @Autowired
    private ABStringBenchmark benchmark;

    static Stream<Arguments> getABStringValues() {
        return Stream.of(
                Arguments.of("3 10 6", "4"),
                Arguments.of("4 18 9", "10"),
                Arguments.of("1 9 8", "6"),
                Arguments.of("1 3 1", "2"),
                Arguments.of("1 3 3", "-1"),
                Arguments.of("7 10 3", "-1"),
                Arguments.of("1 9 9", "-1"),
                Arguments.of("2 6 5", "-1")
        );
    }

    @ParameterizedTest
    @MethodSource("getABStringValues")
    void testProcessLine(String inputLine, String result) {
        String header = "19 1";
        String s = "ABABAABBBAAABABBAAB";
        String[] headers = header.split(" ");
        int n = Integer.parseInt(headers[0]);
        int q = Integer.parseInt(headers[1]);
        List<String> output = abStringSolution.processLines(List.of(header, s, inputLine), n, q, s);
        Assertions.assertEquals(1, output.size());
        Assertions.assertEquals(result, output.get(0));
    }

    @Test
    void testStandard() throws IOException {
        String input = "src/main/java/org/algorithms/test/copilot/ab/input.txt";
        String output = "src/main/java/org/algorithms/test/copilot/ab/output.txt";
        abStringSolution.solve(input, output);

        Path outputPath = Path.of(output);
        Assertions.assertTrue(Files.exists(outputPath), "Output file should exist");

        List<String> outputLines = Files.readAllLines(outputPath);
        Assertions.assertEquals(3, outputLines.size(), "Should contain 3 lines");

        Assertions.assertEquals("3", outputLines.get(0), "Expected result is 3");
        Assertions.assertEquals("3", outputLines.get(1), "Expected result is 3");
        Assertions.assertEquals("-1", outputLines.get(2), "Expected result is -1");
    }

    @Test
    void testLogicBruteForce() {
        int result;
        result = bruteForceValidator.validate("ABAABABBBAAABBABAABABBBABABAA", 1, 20, 19);
        Assertions.assertEquals(17, result, "Expected result is 17");
        result = bruteForceValidator.validate("ABAABABBBAAABBABAABABBBABABAA", 7, 23, 14);
        Assertions.assertEquals(13, result, "Expected result is 13");
        result = bruteForceValidator.validate("ABAABABBBAAABBABAABABBBABABAA", 3, 9, 4);
        Assertions.assertEquals(6, result, "Expected result is 6");
        result = bruteForceValidator.validate("ABAABABBBAAABBABAABABBBABABAA", 1, 29, 14);
        Assertions.assertEquals(12, result, "Expected result is 12");
        result = bruteForceValidator.validate("ABAABABBBAAABBABAABABBBABABAA", 2, 4, 3);
        Assertions.assertEquals(-1, result, "Expected result is -1");
    }

    @Test
    void testQueryLogic() throws IOException {

        Path inputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/input2.txt");
        Path outputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/output2.txt");

        abStringSolution.solve(inputPath.toString(), outputPath.toString());

        List<String> outputLines = Files.readAllLines(outputPath);
        Assertions.assertEquals(6, outputLines.size(), "Should contain 6 lines");
        Assertions.assertEquals("1", outputLines.get(0), "Expected result is 1");
        Assertions.assertEquals("2", outputLines.get(1), "Expected result is 2");
        Assertions.assertEquals("1", outputLines.get(2), "Expected result is 1");
        Assertions.assertEquals("-1", outputLines.get(3), "Expected result is -1");
        Assertions.assertEquals("1", outputLines.get(4), "Expected result is 1");
        Assertions.assertEquals("4", outputLines.get(5), "Expected result is 4");
    }

    @Test
    void testMediumLogic() throws IOException {

        Path inputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/input_test.txt");
        Path outputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/output_test.txt");

        abStringSolution.solve(inputPath.toString(), outputPath.toString());

        List<String> outputLines = Files.readAllLines(outputPath);
        Assertions.assertEquals(5, outputLines.size(), "Should contain 4 lines");

        Assertions.assertEquals("17", outputLines.get(0), "Expected result is 17");
        Assertions.assertEquals("13", outputLines.get(1), "Expected result is 13");
        Assertions.assertEquals("6", outputLines.get(2), "Expected result is 6");
        Assertions.assertEquals("12", outputLines.get(3), "Expected result is 12");
        Assertions.assertEquals("-1", outputLines.get(4), "Expected result is -1");
    }

    @Test
    void testBenchmark() {
        benchmark.runOptimized();
        benchmark.runBruteForce();
        benchmark.compareOutputs();
    }

    @Test
    void testLargeQueryLogic() throws IOException {

        Path inputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/input_very_large.txt");
        Path outputPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/output_very_large.txt");

        abStringSolution.solve(inputPath.toString(), outputPath.toString());

        List<String> inputLines = Files.readAllLines(inputPath);
        List<String> outputLines = Files.readAllLines(outputPath);
        Assertions.assertEquals(1000, outputLines.size(), "Should contain 1000 lines");

        Path validatorPath = Path.of("src/main/java/org/algorithms/test/copilot/ab/input_validator_large.txt");
        List<String> validatorLines = Files.readAllLines(validatorPath);

        String inputValidator = validatorLines.get(0);


        assertManuallySomeSample(inputValidator, outputLines);

        for (int i = 0; i < outputLines.size(); i++) {
            assertBruteForceAgainstLargeString(outputLines, i, inputLines, inputValidator);
        }
    }

    private void assertManuallySomeSample(String inputValidator, List<String> outputLines) {
        int result;
        result = bruteForceValidator.validate(inputValidator, 42581, 42589, 9);
        Assertions.assertEquals(String.valueOf(result), outputLines.get(37), "Expected result is " + result);

        result = bruteForceValidator.validate(inputValidator, 55149, 55193, 6);
        Assertions.assertEquals(String.valueOf(result), outputLines.get(38), "Expected result is " + result);

        result = bruteForceValidator.validate(inputValidator, 95262, 95310, 43);
        Assertions.assertEquals(String.valueOf(result), outputLines.get(564), "Expected result is " + result);

        result = bruteForceValidator.validate(inputValidator, 42814, 42821, 5);
        Assertions.assertEquals(String.valueOf(result), outputLines.get(996), "Expected result is " + result);
    }

    private void assertBruteForceAgainstLargeString(List<String> outputLines, int index, List<String> inputLines, String inputValidator) {
        String outputLine = outputLines.get(index);
        List<Integer> inputs = Arrays.stream(inputLines.get(index + 2).split(" ")).map(Integer::valueOf).toList();
        int result = bruteForceValidator.validate(inputValidator, inputs.get(0), inputs.get(1), inputs.get(2));
        Assertions.assertEquals(String.valueOf(result), outputLine, "Expected result is " + result);
    }
}