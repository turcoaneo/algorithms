package org.algorithms.test.copilot.leet.hard.window;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("minWindow")
public class Solution {
    @TrackExecutionTime
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        int[] required = new int[128]; // ASCII character count
        for (char c : t.toCharArray()) required[c]++;

        int left = 0, right = 0, minStart = 0, minLen = Integer.MAX_VALUE, matched = 0;
        int[] window = new int[128];

        while (right < s.length()) {
            char c = s.charAt(right);
            window[c]++;

            if (required[c] > 0 && window[c] <= required[c]) matched++;

            while (matched == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }

                char leftChar = s.charAt(left);
                window[leftChar]--;
                if (required[leftChar] > 0 && window[leftChar] < required[leftChar]) matched--;

                left++;
            }
            right++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}