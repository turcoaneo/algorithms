package org.algorithms.test.copilot.ab;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Component
public class SumTwoItemsSolution {

    @TrackExecutionTime
    public List<Solution> solveBruteForce(List<Integer> list, int sum) {
        System.out.println("Brute force");
        List<Solution> result = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            Integer first = list.get(i);
            for (int j = i; j < size; j++) {
                Integer second = list.get(j);
                if (first + second == sum) {
                    result.add(new Solution(first, second));
                    System.out.printf("%d + %d = %d%n", first, second, sum);
                }
            }
        }
        return result;
    }

    @TrackExecutionTime
    public List<Solution> solveByTongs(List<Integer> list, int sum) {
        System.out.println("Tongs");
        List<Solution> result = new ArrayList<>();
        int size = list.size();
        for (int i = 0; i < size - 1; i++) {
            Integer first = list.get(i);
            int j = size - 1;
            Integer second = list.get(j);
            if (first + second == sum) {
                result.add(new Solution(first, second));
                System.out.printf("%d + %d = %d%n", first, second, sum);
                continue;
            }
            while (j > i && second + first > sum) {
                j--;
                second = list.get(j);
                if (first + second == sum) {
                    result.add(new Solution(first, second));
                    System.out.printf("%d + %d = %d%n", first, second, sum);
                }
            }
        }
        return result;
    }

    public record Solution(Integer first, Integer second) {
    }

    public static void main(String[] args) {
        SumTwoItemsSolution sol = new SumTwoItemsSolution();
        List<Integer> list = List.of(-11, -7, -4, -3, 0, 1, 3, 6, 10, 11, 15);
        int sum = 11;
        List<Solution> result = sol.solveByTongs(list, sum);
        Assert.isTrue(result.get(0).first() == -4, "Should be -4");
        Assert.isTrue(result.get(0).second() == 15, "Should be 15");
        Assert.isTrue(result.get(1).first() == 0, "Should be 0");
        Assert.isTrue(result.get(1).second() == 11, "Should be 11");
        Assert.isTrue(result.get(2).first() == 1, "Should be 1");
        Assert.isTrue(result.get(2).second() == 10, "Should be 10");
        List<Solution> bruteForce = sol.solveBruteForce(list, sum);
        Assert.isTrue(result.equals(bruteForce), "Lists should be similar");
    }
}