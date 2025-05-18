package org.algorithms.test.copilot.patterns.behavioral.observer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// 1️⃣ Observable Class (Event Dispatcher)
class EventDispatcher {
    private final BlockingQueue<String> eventQueue = new LinkedBlockingQueue<>();

    public void registerObserver(Thread observer) {
        observer.start();
    }

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
}

// 2️⃣ Observer Implementation (Async Event Consumer)
class EventObserver extends Thread {
    private final BlockingQueue<String> eventQueue;

    public EventObserver(EventDispatcher dispatcher) {
        this.eventQueue = dispatcher.getEventQueue();
    }

    @Override
    public void run() {
        try {
            //noinspection InfiniteLoopStatement
            while (true) {
                String event = eventQueue.take(); // Blocks if queue is empty
                System.out.println(Thread.currentThread().getName() + " processed event: " + event);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// 3️⃣ Client Code (Setting Up Observers)
public class ObserverBlockingQueueDemo {
    public static void main(String[] args) {
        EventDispatcher dispatcher = new EventDispatcher();

        EventObserver observer1 = new EventObserver(dispatcher);
        EventObserver observer2 = new EventObserver(dispatcher);

        dispatcher.registerObserver(observer1);
        dispatcher.registerObserver(observer2);

        dispatcher.sendEvent("User Logged In");
        dispatcher.sendEvent("File Uploaded");
        dispatcher.sendEvent("New Message Received");
    }
}
