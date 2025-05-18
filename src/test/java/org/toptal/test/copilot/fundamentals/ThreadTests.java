package org.toptal.test.copilot.fundamentals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.toptal.test.copilot.sort.IterativeMergeSort;
import org.toptal.test.copilot.sort.IterativeQuickSort;
import org.toptal.test.copilot.threads.ThreadPoolExample;

import java.util.Arrays;


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