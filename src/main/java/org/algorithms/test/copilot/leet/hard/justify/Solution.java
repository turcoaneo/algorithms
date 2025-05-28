package org.algorithms.test.copilot.leet.hard.justify;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("fullJustify")
public class Solution {
    @TrackExecutionTime
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            int lineStart = index;
            int lineLen = words[index].length();
            index++;

            while (index < words.length && lineLen + words[index].length() + (index - lineStart) <= maxWidth) {
                lineLen += words[index].length();
                index++;
            }

            int numWords = index - lineStart;
            int numSpaces = maxWidth - lineLen;

            StringBuilder sb = new StringBuilder();
            if (numWords == 1 || index == words.length) {
                for (int i = lineStart; i < index; i++) {
                    sb.append(words[i]);
                    if (i < index - 1) sb.append(" ");
                }
                while (sb.length() < maxWidth) sb.append(" ");
            } else {
                int spaceBetween = numSpaces / (numWords - 1);
                int extraSpace = numSpaces % (numWords - 1);

                for (int i = lineStart; i < index; i++) {
                    sb.append(words[i]);
                    if (i < index - 1) {
                        int spaces = spaceBetween + (i - lineStart < extraSpace ? 1 : 0);
                        sb.append(" ".repeat(spaces));
                    }
                }
            }
            result.add(sb.toString());
        }

        return result;
    }
}