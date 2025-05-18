package org.algorithms.test.copilot.threads;

import org.springframework.stereotype.Component;
import org.algorithms.test.copilot.aop.TrackExecutionTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ThreadPoolExample {
    public static void main(String[] args) {
        ThreadPoolExample app = new ThreadPoolExample();
        int threads = 5;
        app.runCached(threads);
        app.runFixed(threads);
        app.runScheduled(threads);
        app.runScheduledExample(2);
    }

    @TrackExecutionTime
    public void runCached(int threads) {
        ExecutorService cachedPool = null;
        try {
            cachedPool = Executors.newCachedThreadPool();
            loopRun(threads, cachedPool, "cached");
        } finally {
            closePool(cachedPool);
        }
    }

    @TrackExecutionTime
    public void runFixed(int threads) {
        ExecutorService fixedPool = null;
        try {
            fixedPool = Executors.newFixedThreadPool(threads);
            loopRun(threads, fixedPool, "fixed");
        } finally {
            closePool(fixedPool);
        }
    }

    @TrackExecutionTime
    public void runScheduled(int threads) {
        ExecutorService scheduledPool = null;
        try {
            scheduledPool = Executors.newScheduledThreadPool(threads);
            loopRun(threads, scheduledPool, "scheduled");
        } finally {
            closePool(scheduledPool);
        }
    }

    @TrackExecutionTime
    public void runScheduledExample(int threads) {
        ScheduledExecutorService scheduledPool = null;
        try {
            scheduledPool = Executors.newScheduledThreadPool(threads);

            // Task to run every 3 seconds after an initial delay of 1 second
            scheduledPool.scheduleAtFixedRate(() ->
                            System.out.println("Recurring task executed at " + System.currentTimeMillis()),
                    1, 3, TimeUnit.SECONDS);

            // Task to run once after 5 seconds delay
            scheduledPool.schedule(() ->
                            System.out.println("One-time delayed task executed!"),
                    5, TimeUnit.SECONDS);

            // Simulate execution time before shutting down
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.err.println("Exception caught: " + e.getMessage());
            Thread.currentThread().interrupt(); // Preserve interrupt status
        } finally {
            closePool(scheduledPool);
        }

    }

    private static void closePool(ExecutorService pool) {
        if (pool != null) {
            pool.shutdown();
        }
    }

    private static void loopRun(int threads, ExecutorService pool, String type) {
        for (int i = 1; i <= threads; i++) {
            final int taskId = i;
            pool.submit(() -> System.out.println("Task " + taskId + " running on " + type + " " +
                    Thread.currentThread().getName()));
        }
    }
}