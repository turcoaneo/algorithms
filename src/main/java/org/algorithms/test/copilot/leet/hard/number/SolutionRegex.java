package org.algorithms.test.copilot.leet.hard.number;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

@Component("isNumberRegex")
public class SolutionRegex {
    @TrackExecutionTime
    public boolean isNumber(String s) {
        return s.matches("[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?");
    }
}