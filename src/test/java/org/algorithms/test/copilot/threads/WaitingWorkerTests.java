package org.algorithms.test.copilot.threads;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class WaitingWorkerTests {
    private static final int LIMIT_SIZE = 5;

    @Test
    public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch readyThreadCounter = new CountDownLatch(LIMIT_SIZE);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(LIMIT_SIZE);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new WaitingWorker(
                        outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
                .limit(LIMIT_SIZE)
                .toList();

        workers.forEach(Thread::start);
        readyThreadCounter.await();
        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        outputScraper.add("Workers complete");

        assertThat(outputScraper)
                .containsExactly(
                        "Workers ready",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Counted down",
                        "Workers complete"
                );
    }

    @Test
    public void whenFailingToParallelProcess_thenMainThreadShouldGetNotGetStuck()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(LIMIT_SIZE);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new WaitingBrokenWorker(outputScraper, countDownLatch)))
                .limit(LIMIT_SIZE)
                .toList();

        workers.forEach(Thread::start);
        boolean completed = countDownLatch.await(100L, TimeUnit.MILLISECONDS);
        assertThat(completed).isFalse();
    }
}