package org.algorithms.test.copilot.leet.medium.dna;

import java.util.List;

public class RepeatedDNASequence {
    public static void main(String[] args) {
        RepeatedDNASequence obj = new RepeatedDNASequence();
        obj.findSequence("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT");
        obj.findSequence("AAAAAAAAAAAAA");
    }

    private void findSequence(String input) {
        Solution solution = new Solution();
        List<String> output = solution.findRepeatedDnaSequencesClassic(input);
        System.out.println("Output: " + output);
    }
}
