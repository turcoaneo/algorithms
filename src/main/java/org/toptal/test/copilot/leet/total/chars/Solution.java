package org.toptal.test.copilot.leet.total.chars;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.List;

@Component("lengthAfterTransformations")
public class Solution {

    final int MOD = 1_000_000_007; // Prevent overflow

    @TrackExecutionTime
    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        int size = nums.size();
        int[] freq = new int[size];

        // Initialize character frequencies from input string
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Build the combination matrix
        int[][] combinationMatrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            int shift = nums.get(i);
            for (int j = 1; j <= shift; j++) {
                int newIndex = (i + j) % size;
                combinationMatrix[i][newIndex] = 1; // Influence characters accordingly
            }
        }

        // Compute matrix exponentiation: T = combinationMatrix ^ t
        int[][] T = matrixExponentiation(combinationMatrix, t, size);

        // Multiply freq vector with T
        int[] finalFreq = matrixVectorMultiplication(T, freq, size);

        // Compute final result
        long result = 0;
        for (int value : finalFreq) {
            result = (result + value) % MOD;
        }

        return (int) result;
    }

    // Matrix exponentiation using fast power
    private int[][] matrixExponentiation(int[][] matrix, int exponent, int size) {
        int[][] result = new int[size][size];
        int[][] base = matrix.clone(); // Copy matrix for modifications

        // Initialize result as identity matrix
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = matrixMultiplication(result, base, size); // Apply transformation
            }
            base = matrixMultiplication(base, base, size); // Square the matrix
            exponent >>= 1; // Halve the exponent
        }

        return result;
    }

    // Matrix multiplication (modular)
    private int[][] matrixMultiplication(int[][] A, int[][] B, int size) {
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                long sum = 0; // Use long to accumulate values safely
                for (int k = 0; k < size; k++) {
                    sum = (sum + (long) A[i][k] * B[k][j]) % MOD; // Prevent overflow
                }
                result[i][j] = (int) sum; // Convert safely back to int
            }
        }

        return result;
    }
    private int[] matrixVectorMultiplication(int[][] matrix, int[] vector, int size) {
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            long sum = 0; // Keep accumulation in long to prevent overflow
            for (int j = 0; j < size; j++) {
                sum += (long) matrix[i][j] * vector[i]; // Accumulate without modulo first
            }
            result[i] = (int) (sum % MOD); // Apply modulo at the very end
        }

        return result;
    }


    @TrackExecutionTime
    public int bruteForceLengthAfterTransformations(String s, int t, List<Integer> nums) {
        final int MOD = 1_000_000_007;

        StringBuilder transformed = new StringBuilder(s);

        for (int step = 0; step < t; step++) {
            StringBuilder newString = new StringBuilder();
            for (char ch : transformed.toString().toCharArray()) {
                int index = ch - 'a';
                int shift = nums.get(index);
                for (int i = 1; i <= shift; i++) {
                    int size = nums.size();
                    char newChar = (char) ('a' + (index + i) % size); // Wrap around
                    newString.append(newChar);
                }
            }
            transformed = newString;
            transformed.setLength(Math.min(transformed.length(), MOD)); // Modulo constraint
        }

        return transformed.length();
    }


    @TrackExecutionTime
    public int lengthAfterTransformationsString(String s, int t, List<Integer> nums) {
        final int MOD = 1_000_000_007;
        int size = nums.size();
        int[] charFrequency = new int[size];

        // Step 1: Initialize frequency array from input string
        for (char ch : s.toCharArray()) {
            charFrequency[ch - 'a']++;
        }

//         Step 2: Perform transformations iteratively
        //noinspection DuplicatedCode
        for (int step = 0; step < t; step++) {
            //noinspection DuplicatedCode
            int[] newFrequency = new int[size]; // Store new frequencies after transformation

            for (int i = 0; i < size; i++) {
                if (charFrequency[i] > 0) {
                    int shift = nums.get(i);
                    for (int j = 0; j < shift; j++) {
                        int newIndex = (i + j + 1) % size; // Ensure wrap-around beyond 'z'
                        newFrequency[newIndex] += charFrequency[i];
                        newFrequency[newIndex] %= MOD; // Avoid overflow
                    }
                }
            }

            charFrequency = newFrequency; // Update frequency array
        }

        // Step 3: Compute final string length
        long result = 0;
        for (int freq : charFrequency) {
            result = (result + freq) % MOD;
        }

        return (int) result % MOD;
    }


}