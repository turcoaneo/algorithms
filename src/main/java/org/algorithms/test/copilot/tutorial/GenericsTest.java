package org.algorithms.test.copilot.tutorial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsTest {
    public static double sumNumbers(List<? extends Number> numbers) {
        // Your code here
        return numbers.stream().map(Number::doubleValue).reduce(0D, Double::sum);
    }

    public static void addInteger(List<? super Integer> list) {
        // Your code here
        if (list.isEmpty()) {
            list.add(0);
        }
        list.replaceAll(obj -> Integer.parseInt((String.valueOf(obj))) + 42);
    }

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(1, 2, 3);
        System.out.println(sumNumbers(ints)); // should print 6.0

        List<Number> nums = new ArrayList<>();
        addInteger(nums);
        System.out.println(nums); // should print [42]
        nums = Arrays.asList(1_0, 20, 30L);
        addInteger(nums);
        System.out.println(nums); // should print [52, 62, 72]
    }
}