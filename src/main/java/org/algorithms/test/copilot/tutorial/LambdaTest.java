package org.algorithms.test.copilot.tutorial;

import java.util.*;
import java.util.stream.*;

@FunctionalInterface
interface Transformer {
    String transform(String input);

    // static method identity()
    static Transformer identity(){
        return input -> input;
    }
    default Transformer andThen(Transformer after) {
        return input -> after.transform(this.transform(input));
    }
}

public class LambdaTest {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("java", "rocks", "lambda");

        // Lambda implementation of Transformer
        Transformer upper = String::toUpperCase;

        List<String> result = words.stream()
                                   .map(upper::transform)
                                   .collect(Collectors.toList());

        System.out.println(result); // should print [JAVA, ROCKS, LAMBDA]

        Transformer id = Transformer.identity();
        System.out.println(id.transform("unchanged")); // should print unchanged

        Transformer shout = input -> input + "!";
        Transformer combo = upper.andThen(shout);
        System.out.println(combo.transform("java")); // prints JAVA!
    }
}