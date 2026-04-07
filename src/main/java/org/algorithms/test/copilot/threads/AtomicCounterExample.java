package org.algorithms.test.copilot.threads;

import org.algorithms.test.copilot.aop.TrackExecutionTime;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.StringTemplate.STR;

@Component
public class AtomicCounterExample {

    public int getThreadPoolSize() {
        return THREAD_POOL_SIZE;
    }

    private final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private int size = 10;
    private boolean hasPrint = false;

    private final AtomicInteger counter = new AtomicInteger(0);
    private final ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public AtomicInteger getCounter() {
        return counter;
    }

    public static void main(String[] args) throws Exception {
        AtomicCounterExample service = new AtomicCounterExample();
        System.out.println(STR."THREAD POOL SIZE: \{service.THREAD_POOL_SIZE}");
        service.hasPrint = false;

        service.runStructuredThreads();
        System.out.println(STR."Final Counter Value: \{service.counter.get()}");
        System.out.printf("Final Counter Value equals SIZE %s%n", service.counter.get() == service.size);

        service.counter.set(0);
        service.runCompletableThreads();
        System.out.println(STR."Final Counter Value: \{service.counter.get()}");
        System.out.printf("Final Counter Value equals SIZE %s%n", service.counter.get() == service.size);

        service.counter.set(0);
        service.runStructuredThreads();
        System.out.println(STR."Final Counter Value: \{service.counter.get()}");
        System.out.printf("Final Counter Value equals SIZE %s%n", service.counter.get() == service.size);
    }

    @TrackExecutionTime
    public void runStructuredThreads() {
        populateQueue();

        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                scope.fork(() -> {
                    while (!queue.isEmpty()) {
                        Integer val = queue.poll();
                        if (val != null) {
                            int updated = counter.incrementAndGet();
                            printIntermediaryResults(val, updated);
                        }
                    }
                    return null;
                });
            }

            try {
                scope.join();   // Wait for all tasks
                scope.throwIfFailed(); // Propagate exceptions if any
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @TrackExecutionTime
    public void runCompletableThreads() {
        populateQueue();

        var futures = java.util.stream.IntStream.range(0, THREAD_POOL_SIZE)
                .mapToObj(_ -> java.util.concurrent.CompletableFuture.runAsync(() -> {
                    while (!queue.isEmpty()) {
                        Integer val = queue.poll();
                        if (val != null) {
                            int updated = counter.incrementAndGet();
                            printIntermediaryResults(val, updated);
                        }
                    }
                }))
                .toList();

        futures.forEach(java.util.concurrent.CompletableFuture::join);
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
                if (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.err.println("Timeout waiting for threads!");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void printIntermediaryResults(Integer val, int updatedCount) {
        if (hasPrint) {
            System.out.println(STR."\{Thread.currentThread().getName()} processed: \{val} | Counter: \{updatedCount}");
        }
    }

    private void populateQueue() {
        queue.clear();
        for (int i = 1; i <= size; i++) {
            queue.offer(i);
        }
    }
}