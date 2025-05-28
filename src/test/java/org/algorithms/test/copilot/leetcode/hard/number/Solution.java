package org.algorithms.test.copilot.leetcode.hard.number;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("isNumber")
public class Solution {
    @TrackExecutionTime
    public boolean isNumber(String s) {
        boolean numSeen = false, dotSeen = false, expSeen = false;
        int numAfterExp = 0;

        s = s.trim(); // Remove leading/trailing spaces

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                numSeen = true;
                if (expSeen) numAfterExp++;
            } else if (c == '.') {
                if (dotSeen || expSeen) return false;
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (expSeen || !numSeen) return false;
                expSeen = true;
                numSeen = false; // Reset to check digits after 'e'
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') return false;
            } else {
                return false;
            }
        }

        return numSeen && (!expSeen || numAfterExp > 0);
    }
}