package org.algorithms.test.copilot.tutorial;

import java.util.*;

public class DuplicateChecker {
    public static boolean hasDuplicates(String[] arr) {
        // Your code here -> I would go for a map
		Map<String, Integer> aux = new HashMap<>();
		for(String s : arr) {
			Integer n = aux.getOrDefault(s, 0);
			if (n > 0) return true;	
			aux.put(s, 1);
		}
		return false;
    }

    public static void main(String[] args) {
        String[] input = {"apple", "banana", "cherry", "apple"};
        System.out.println(hasDuplicates(input)); // should print true
    }
}