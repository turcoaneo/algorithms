package org.algorithms.test.copilot.threads;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class WaitingBrokenWorker implements Runnable {

    private final List<String> outputScraper;
    private final CountDownLatch countDownLatch;
    private final boolean isTrue;

    public WaitingBrokenWorker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
        this.isTrue = true;
    }

    @Override
    public void run() {
        if (isTrue) {
            throw new RuntimeException("Oh dear, I'm a BrokenWorker");
        }
        countDownLatch.countDown();
        outputScraper.add("Counted down");
    }
}
