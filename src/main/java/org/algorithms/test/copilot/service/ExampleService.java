package org.algorithms.test.copilot.service;

import org.springframework.stereotype.Service;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

@Service
public class ExampleService {

    @TrackExecutionTime
    public void sampleMethod() throws InterruptedException {
        Thread.sleep(500); // Simulate some processing time
        System.out.println("Method completed!");
    }
}