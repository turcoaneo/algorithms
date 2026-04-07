package org.algorithms.test.copilot.threads;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class AtomicCounterExampleTests {
    @Autowired AtomicCounterExample service;

    @Test
    void test() {
        service.setSize(10_000_000);
        System.out.println(STR."Size = \{service.getSize()}, Thread pool size = \{service.getThreadPoolSize()}");
        service.runStructuredThreads();
        Assertions.assertEquals(service.getCounter().get(), service.getSize());
        service.getCounter().set(0);
        service.runThreads();
        Assertions.assertEquals(service.getCounter().get(), service.getSize());
        service.getCounter().set(0);
        service.runCompletableThreads();
        Assertions.assertEquals(service.getCounter().get(), service.getSize());
    }
}