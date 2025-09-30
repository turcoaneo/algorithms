package org.algorithms.test.copilot.tutorial;

import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class GroupingTest {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("Java", "rocks", "is", "cool", "Lambda", "fun");

        // Your stream magic here
        Map<Integer, List<String>> lengthString = new HashMap<>();
                words.forEach(s -> {
                    int key = s.length();
                    List<String> value = lengthString.getOrDefault(key, new ArrayList<>());
                    lengthString.putIfAbsent(key, value);
                    value.add(s);
                });//instead of Collectors.groupingBy
        System.out.println(lengthString); //{2=[is], 3=[fun], 4=[Java, cool], 5=[rocks], 6=[Lambda]}

        Map<Integer, List<String>> grouped = words.stream()
//                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                Function.identity()
                        )
                ));
        System.out.println(grouped);
    }
}