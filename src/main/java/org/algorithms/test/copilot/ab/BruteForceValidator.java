package org.algorithms.test.copilot.ab;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component
public class BruteForceValidator {

    @TrackExecutionTime
    public int validate(String s, int l, int r, int k) {
        String sub = s.substring(l - 1, r);
        char charAt = sub.charAt(k - 1);
        char oppositeChar = charAt == 'A' ? 'B' : 'A';

        int charPosition = 0;
        for (int i = 0; i < k; i++) {
            if (sub.charAt(i) == charAt) {
                charPosition++;
            }
        }

        int oppositeCharPosition = 0;
        for (int i = 0; i < sub.length(); i++) {
            if (sub.charAt(i) == oppositeChar) {
                oppositeCharPosition++;
                if (oppositeCharPosition == charPosition) {
                    return i + 1;
                }
            }
        }

        return -1;
    }
}