package org.algorithms.test.copilot.leet.hard.window;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("minWindowMap")
public class SolutionMap {
    @TrackExecutionTime
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        Map<Character, Integer> required = new HashMap<>();
        for (char c : t.toCharArray()) required.put(c, required.getOrDefault(c, 0) + 1);

        int left = 0, right = 0, minStart = 0, minLen = Integer.MAX_VALUE, matched = 0;
        Map<Character, Integer> window = new HashMap<>();

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (required.containsKey(c) && window.get(c).equals(required.get(c))) matched++;

            while (matched == required.size()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);
                if (required.containsKey(leftChar) && window.get(leftChar) < required.get(leftChar)) matched--;

                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}