package org.algorithms.test.copilot.threads;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AtomicCounterExample {
    private final int THREAD_POOL_SIZE = 4;

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    private int size = 10;

    public AtomicInteger getCounter() {
        return counter;
    }

    private boolean hasPrint = false;

    private final AtomicInteger counter = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        AtomicCounterExample service = new AtomicCounterExample();
        service.hasPrint = true;
        service.runCompletableThreads();
        System.out.println("Final Counter Value: " + service.counter.get()); // Ensures final count after execution
        System.out.printf("Final Counter Value equals SIZE %s", service.counter.get() == service.size); // check value
        System.out.println();
        service.counter.set(0);
        service.runThreads();
        System.out.println("Final Counter Value: " + service.counter.get()); // Ensures final count after execution
        System.out.printf("Final Counter Value equals SIZE %s", service.counter.get() == service.size); // check value
        System.out.println();
    }

    @TrackExecutionTime
    public void runThreads() {
        @SuppressWarnings("resource")
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

        try {
            populateQueue();

            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                threadPool.submit(() -> {
                    while (!queue.isEmpty()) {
                        Integer val = queue.poll();
                        if (val != null) {
                            int updatedCount = counter.incrementAndGet();
                            printIntermediaryResults(val, updatedCount);
                        }
                    }
                });
            }
        } finally {
            threadPool.shutdown();
            try {
                // Wait for all tasks to finish
                if (!threadPool.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.err.println("Timeout waiting for threads!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @TrackExecutionTime
    public void runCompletableThreads() {
        populateQueue();

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            futures.add(CompletableFuture.runAsync(() -> {
                while (!queue.isEmpty()) {
                    Integer val = queue.poll();
                    if (val != null) {
                        int updatedCount = counter.incrementAndGet();
                        printIntermediaryResults(val, updatedCount);
                    }
                }
            }));
        }

        // Wait for all tasks
        futures.forEach(CompletableFuture::join);
    }

    private void printIntermediaryResults(Integer val, int updatedCount) {
        if (hasPrint) {
            System.out.println(Thread.currentThread().getName() +
                    " processed: " + val + " | Counter: " + updatedCount);
        }
    }

    private void populateQueue() {
        for (int i = 1; i <= size; i++) {
            queue.offer(i);
        }
    }
}
