package org.algorithms.test.copilot.threads;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterExample {
    private static final int THREAD_POOL_SIZE = 4;
    private static final AtomicInteger counter = new AtomicInteger(0);
    private static final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        populateQueue();

        try {
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                threadPool.submit(() -> {
                    while (!queue.isEmpty()) {
                        Integer val = queue.poll();
                        if (val != null) {
                            int updatedCount = counter.incrementAndGet();
                            System.out.println(Thread.currentThread().getName() + " processed: " + val + " | Counter: " + updatedCount);
                        }
                    }
                });
            }
        } finally {
            threadPool.shutdown();
        }

        System.out.println("Final Counter Value: " + counter.get()); // ✅ Ensures final count after execution
    }

    private static void populateQueue() {
        for (int i = 1; i <= 10; i++) {
            queue.offer(i);
        }
    }
}
