package org.algorithms.test.copilot.tutorial;

import java.util.*;

public class MaxUtil {
    public static <T extends Comparable<? super T>> T max(List<T> list) {
        // Your code here
        T max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(max) > 0) {
                max = list.get(i);
            }
        }
        return max;
    }

    public static <T> T max(List<T> list, Comparator<? super T> comp) {
        // Your code here
        return list.stream().max(comp).orElse(null);
    }
    public static void main(String[] args) {
        List<String> words = Arrays.asList("banana", "apple", "cherry");
        String maxDefault = max(words);
        System.out.println(maxDefault); // should print cherry

        Comparator<String> byLength = Comparator.comparingInt(String::length);
        String maxByLength = max(words, byLength);
        System.out.println(maxByLength); // should print banana

        List<Integer> integers = Arrays.asList(4, 3, 2, 1, 5, 0);
        Integer max = max(integers);
        System.out.println(max); // should print 5

        @SuppressWarnings("rawtypes")
        List raw = Arrays.asList(4, 3, 2, 1, 5, 0);
        //noinspection unchecked
        System.out.println(max(raw)); // should print 5

        raw = Arrays.asList(4, 3, 2, 1, 5, 0);
        //noinspection unchecked
        Object max1 = max(raw);
        System.out.println((int)max1); // should print 5
        System.out.println(max1); // should print 5
    }
}