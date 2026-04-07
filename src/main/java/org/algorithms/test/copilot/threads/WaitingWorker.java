package org.algorithms.test.copilot.threads;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WaitingWorker implements Runnable {

    private final List<String> outputScraper;
    private final CountDownLatch readyThreadCounter;
    private final CountDownLatch callingThreadBlocker;
    private final CountDownLatch completedThreadCounter;

    public WaitingWorker(
            List<String> outputScraper,
            CountDownLatch readyThreadCounter,
            CountDownLatch callingThreadBlocker,
            CountDownLatch completedThreadCounter) {

        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {
        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            Thread.sleep(1000);
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
        } finally {
            completedThreadCounter.countDown();
        }
    }
}
