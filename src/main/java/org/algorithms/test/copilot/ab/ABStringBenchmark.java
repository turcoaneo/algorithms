package org.algorithms.test.copilot.ab;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ABStringBenchmark {

    private final ABStringSolution optimizedSolution;
    private final BruteForceValidator bruteForceValidator;

    public ABStringBenchmark(ABStringSolution optimizedSolution, BruteForceValidator bruteForceValidator) {
        this.optimizedSolution = optimizedSolution;
        this.bruteForceValidator = bruteForceValidator;
    }

    private static final String INPUT_PATH = "src/main/java/org/algorithms/test/copilot/ab/input_extra_large.txt";
    private static final String OUTPUT_OPTIMIZED = "src/main/java/org/algorithms/test/copilot/ab/output_optimized.txt";
    private static final String OUTPUT_BRUTE_FORCE = "src/main/java/org/algorithms/test/copilot/ab/output_brute_force.txt";
    private static final String VALIDATOR_PATH = "src/main/java/org/algorithms/test/copilot/ab" +
            "/input_validator_extra_large.txt";

    @TrackExecutionTime
    public void runOptimized() {
        optimizedSolution.solve(INPUT_PATH, OUTPUT_OPTIMIZED);
    }

    @TrackExecutionTime
    public void runBruteForce() {
        try {
            List<String> inputLines = Files.readAllLines(Path.of(INPUT_PATH));
            List<String> validatorLines = Files.readAllLines(Path.of(VALIDATOR_PATH));
            String inputValidator = validatorLines.get(0);

            List<String> outputLines = new ArrayList<>();

            for (int i = 2; i < inputLines.size(); i++) {
                List<Integer> inputs = Arrays.stream(inputLines.get(i).split(" "))
                        .map(Integer::valueOf)
                        .toList();

                int result = bruteForceValidator.validate(inputValidator, inputs.get(0), inputs.get(1), inputs.get(2));
                outputLines.add(String.valueOf(result));
            }

            Files.write(Path.of(OUTPUT_BRUTE_FORCE), outputLines);

        } catch (IOException e) {
            System.err.println("Error in brute force execution: " + e.getMessage());
        }
    }

    @TrackExecutionTime
    public void compareOutputs() {
        try {
            List<String> optimized = Files.readAllLines(Path.of(OUTPUT_OPTIMIZED));
            List<String> bruteForce = Files.readAllLines(Path.of(OUTPUT_BRUTE_FORCE));

            if (optimized.size() != bruteForce.size()) {
                System.out.println("Mismatch in output sizes: " + optimized.size() + " vs " + bruteForce.size());
                return;
            }

            int mismatches = 0;
            for (int i = 0; i < optimized.size(); i++) {
                if (!optimized.get(i).equals(bruteForce.get(i))) {
                    System.out.printf("Mismatch at line %d: Optimized=%s, BruteForce=%s%n", i, optimized.get(i), bruteForce.get(i));
                    mismatches++;
                }
            }

            if (mismatches == 0) {
                System.out.println("All outputs match perfectly.");
            } else {
                System.out.println("Total mismatches: " + mismatches);
            }

        } catch (IOException e) {
            System.err.println("Error comparing outputs: " + e.getMessage());
        }
    }
}