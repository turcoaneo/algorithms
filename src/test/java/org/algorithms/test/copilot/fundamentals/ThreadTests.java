package org.algorithms.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.algorithms.test.copilot.threads.ThreadPoolExample;


@SpringBootTest
public class ThreadTests {
    @Autowired
    ThreadPoolExample threadPoolExample;
    int threads = 100;

    @Test
    void testRunCached() {
        threadPoolExample.runCached(threads);
        Assertions.assertTrue(true);
    }

    @Test
    void testRunFixed() {
        threadPoolExample.runFixed(threads);
        Assertions.assertTrue(true);
    }

    @Test
    void testRunScheduled() {
        threadPoolExample.runScheduled(threads);
        Assertions.assertTrue(true);
    }
}