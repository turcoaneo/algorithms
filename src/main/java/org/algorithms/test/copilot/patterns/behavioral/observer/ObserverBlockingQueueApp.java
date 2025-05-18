package org.algorithms.test.copilot.patterns.behavioral.observer;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

// 1️⃣ Event Dispatcher (BlockingQueue for message passing)
class EventDispatcherCmd {
    private final BlockingQueue<String> eventQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public void sendEvent(String event) {
        try {
            eventQueue.put(event); // Blocks if queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public BlockingQueue<String> getEventQueue() {
        return eventQueue;
    }

    public boolean isRunning() {
        return running;
    }

    public void stop() {
        running = false;
    }
}

// 2️⃣ Observer Thread (Processes messages asynchronously)
class EventObserverCmd extends Thread {
    private final BlockingQueue<String> eventQueue;
    private final EventDispatcherCmd dispatcher;

    public EventObserverCmd(EventDispatcherCmd dispatcher, String name) {
        super(name);
        this.eventQueue = dispatcher.getEventQueue();
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {
        try {
            while (dispatcher.isRunning()) {
                String event = eventQueue.take(); // Blocks until a message is received
                int sleepTime = ThreadLocalRandom.current().nextInt(1, 4) * 1000; // 1-3 sec delay
                //noinspection BusyWait
                Thread.sleep(sleepTime);
                System.out.println(Thread.currentThread().getName() + " processed event: " + event + " (after " + sleepTime / 1000 + " sec)");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// 3️⃣ Client Code (Handles command-line input and stops on "stop")
public class ObserverBlockingQueueApp {
    public static void main(String[] args) {
        EventDispatcherCmd dispatcher = new EventDispatcherCmd();

        EventObserverCmd observer1 = new EventObserverCmd(dispatcher, "Observer-1");
        EventObserverCmd observer2 = new EventObserverCmd(dispatcher, "Observer-2");
        EventObserverCmd observer3 = new EventObserverCmd(dispatcher, "Observer-3");

        observer1.start();
        observer2.start();
        observer3.start();

        // Command-line input handling
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message (type 'stop' to exit): ");
            String input = scanner.nextLine();

            if ("stop".equalsIgnoreCase(input)) {
                dispatcher.stop();
                break;
            }

            dispatcher.sendEvent(input);
        }

        scanner.close();
        System.out.println("Application stopped!");
    }
}
