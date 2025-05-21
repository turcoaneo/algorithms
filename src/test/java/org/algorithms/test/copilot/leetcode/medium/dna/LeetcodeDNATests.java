package org.algorithms.test.copilot.leetcode.medium.dna;

import org.algorithms.test.copilot.leet.medium.dna.Solution;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class LeetcodeDNATests {
    @Autowired
    Solution repeatedDNASequence;

    @Test
    void testDNA() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> expected = Arrays.asList("AAAAACCCCC", "CCCCCAAAAA");
        List<String> result = repeatedDNASequence.findRepeatedDnaSequencesClassic(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesRolling(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesBit(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesTrie(s);
        Assertions.assertEquals(expected, result);

        s = "AAAAAAAAAAAAA";
        expected = List.of("AAAAAAAAAA");
        result = repeatedDNASequence.findRepeatedDnaSequencesClassic(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesRolling(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesBit(s);
        Assertions.assertEquals(expected, result);
        result = repeatedDNASequence.findRepeatedDnaSequencesTrie(s);
        Assertions.assertEquals(expected, result);
    }
}