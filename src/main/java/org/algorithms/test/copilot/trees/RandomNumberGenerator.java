package org.algorithms.test.copilot.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomNumberGenerator {

    public static List<Integer> generateRandomList ( int N, int length){
        Random rand = new Random();
        return rand.ints(length, 0, N) // Generate stream of random integers
                .boxed()
                .collect(Collectors.toList());
    }
    public static List<Integer> generateRandomList(int length) {
        Random rand = new Random();
        List<Integer> randomList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            randomList.add(rand.nextInt()); // Generates any integer
        }

        return randomList;
    }

    public static void main(String[] args) {
        int length = 10;
        List<Integer> randomList = generateRandomList(length);
        System.out.println("Random List: " + randomList);
        List<Integer> randomList2 = generateRandomList(length);
        System.out.println("Random List: " + randomList2);


    }
}