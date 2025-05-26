package org.algorithms.test.copilot.threads;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentDataStructuresExample {
    private static final int THREAD_POOL_SIZE = 4;
    private static final int RUN_TIME_MS = 50;
    private static final Random random = new Random();

    private static final ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
    private static final ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();
    private static final CopyOnWriteArrayList<Integer> cowArrayList = new CopyOnWriteArrayList<>();
    private static final LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
    private static final ConcurrentSkipListMap<String, Integer> skipListMap = new ConcurrentSkipListMap<>();

    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        populateDataStructures(); // Fill collections initially

        long startTime = System.currentTimeMillis();

        try {
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                threadPool.submit(() -> {
                    while (System.currentTimeMillis() - startTime < RUN_TIME_MS && !isAnyCollectionEmpty()) {
                        consumeRandomStructure();
                    }
                });
            }
        } finally {
            threadPool.shutdown();
            try {
                boolean b = threadPool.awaitTermination(2, TimeUnit.SECONDS);// Wait for tasks to finish
                System.out.println("Awaited: " + b);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted during shutdown.");
            }

        }
        System.out.println("Counter: " + counter.get());
    }

    private static void populateDataStructures() {
        for (int i = 1; i <= 10; i++) {
            linkedQueue.offer(i);
            hashMap.put("Key" + i, i);
            cowArrayList.add(i);
            blockingQueue.offer(i);
            skipListMap.put("SortedKey" + i, i);
        }
    }

    private static boolean isAnyCollectionEmpty() {
        return linkedQueue.isEmpty() || hashMap.isEmpty() || cowArrayList.isEmpty() || blockingQueue.isEmpty() || skipListMap.isEmpty();
    }

    private static void consumeRandomStructure() {
        String threadName = Thread.currentThread().getName();
        int choice = random.nextInt(5);
        int c = counter.incrementAndGet();

        switch (choice) {
            case 0 -> {
                Integer val = linkedQueue.poll();
                System.out.println(threadName + " consumed from LinkedQueue: " + val + ". Counter: " + c);
            }
            case 1 -> {
                String key = "Key" + random.nextInt(10);
                Integer val = hashMap.remove(key);
                System.out.println(threadName + " removed from HashMap: " + key + " -> " + val + ". Counter: " + c);
            }
            case 2 -> {
                Integer val = !cowArrayList.isEmpty() ? cowArrayList.remove(random.nextInt(cowArrayList.size())) : null;
                System.out.println(threadName + " removed from CopyOnWriteArrayList: " + val + ". Counter: " + c);
            }
            case 3 -> {
                Integer val = blockingQueue.poll();
                System.out.println(threadName + " consumed from BlockingQueue: " + val + ". Counter: " + c);
            }
            case 4 -> {
                String key = "SortedKey" + random.nextInt(10);
                Integer val = skipListMap.remove(key);
                System.out.println(threadName + " removed from SkipListMap: " + key + " -> " + val + ". Counter: " + c);
            }
        }
    }
}
