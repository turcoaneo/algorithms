package org.toptal.test.copilot.leet.total.dna;

import org.springframework.stereotype.Component;
import org.toptal.test.copilot.aop.TrackExecutionTime;

import java.util.*;

@Component("repeatedDnaSequences")
public class Solution {
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        int count = 0; // Tracks how often a sequence appears
    }

    public List<String> findRepeatedDnaSequencesTrie(String s) {
        int len = s.length();
        if (len < 10) return new ArrayList<>();

        TrieNode root = new TrieNode();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i <= len - 10; i++) {
            String sub = s.substring(i, i + 10);
            if (insert(root, sub)) {
                repeated.add(sub); // Already exists more than once, add to result
            }
        }

        return new ArrayList<>(repeated);
    }

    private boolean insert(TrieNode node, String seq) {
        TrieNode current = node;
        for (char c : seq.toCharArray()) {
            current.children.putIfAbsent(c, new TrieNode());
            current = current.children.get(c);
        }
        current.count++;
        return current.count == 2; // Return true when a sequence appears the second time
    }

    @TrackExecutionTime
    public List<String> findRepeatedDnaSequencesBit(String s) {
        int len = s.length();
        int THRESHOLD = 10;
        if (len < THRESHOLD) return new ArrayList<>();

        Map<Character, Integer> map = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);
        int BIT_MASK = 20;
        int mask = (1 << BIT_MASK) - 1; // 20-bit mask for DNA sequences
        int hash = 0;
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i < len; i++) {
            hash = ((hash << 2) | map.get(s.charAt(i))) & mask; // Shift left, add new char, keep last 20 bits
            int size = THRESHOLD - 1;
            if (i >= size) { // Window reaches size 10
                if (seen.contains(hash)) {
                    repeated.add(s.substring(i - size, i + 1)); // Extract original sequence
                } else {
                    seen.add(hash);
                }
            }
        }

        return new ArrayList<>(repeated);
    }


    @TrackExecutionTime
    public List<String> findRepeatedDnaSequencesRolling(String s) {
        int len = s.length();
        int THRESHOLD = 10;
        int WINDOW = THRESHOLD - 1;
        if (len < THRESHOLD) return new ArrayList<>();

        Map<Character, Integer> map = Map.of('A', 0, 'C', 1, 'G', 2, 'T', 3);
        int base = 4, hash = 0, mod = (int) Math.pow(base, WINDOW); // Modulo for rolling hash
        Set<Integer> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i < len; i++) {
            hash = hash * base + map.get(s.charAt(i)); // Add new char to hash
            if (i >= WINDOW) { // Window reaches size 10
                if (seen.contains(hash)) {
                    repeated.add(s.substring(i - WINDOW, i + 1)); // Extract original sequence
                } else {
                    seen.add(hash);
                }
                hash -= map.get(s.charAt(i - WINDOW)) * mod; // Remove leftmost character
            }
        }

        return new ArrayList<>(repeated);
    }


    @TrackExecutionTime
    public List<String> findRepeatedDnaSequencesClassic(String s) {
        int len = s.length();
        if (len < 10) return new ArrayList<>();

        Set<String> seen = new HashSet<>();
        Set<String> repeated = new HashSet<>();

        for (int i = 0; i <= len - 10; i++) {
            String substring = s.substring(i, i + 10);
            if (seen.contains(substring)) {
                repeated.add(substring); // Already seen, so it's repeated
            } else {
                seen.add(substring); // First time seeing this sequence
            }
        }

        return new ArrayList<>(repeated);
    }
}
