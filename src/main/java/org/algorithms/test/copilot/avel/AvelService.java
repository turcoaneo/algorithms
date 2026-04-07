package org.algorithms.test.copilot.avel;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class AvelService {

    @TrackExecutionTime
    public int sampleMethod() {
        System.out.println("Method completed!");
        return 0;
    }

    @TrackExecutionTime
    public int sampleMethod(int input) {
        System.out.println("Method completed!");
        return input + 1;
    }

    public static void main(String... args) {
//        AltimateService service = new AltimateService();
//        System.out.println(STR."Sample method result: \{service.sampleMethod()}");
//        System.out.println(STR."Sample method result: \{service.sampleMethod(0)}");
    }
}