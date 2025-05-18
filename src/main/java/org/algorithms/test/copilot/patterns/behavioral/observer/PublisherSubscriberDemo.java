package org.algorithms.test.copilot.patterns.behavioral.observer;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

// 1️⃣ Event Publisher (Manages Subscribers)
class EventPublisher {
    private final List<EventSubscriber> subscribers = new ArrayList<>();

    public void registerSubscriber(EventSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void publishEvent(String event) {
        for (EventSubscriber subscriber : subscribers) {
            subscriber.receiveEvent(event);
        }
    }
}

// 2️⃣ Subscriber (Processes events with random sleep)
class EventSubscriber {
    private final String name;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EventSubscriber(String name) {
        this.name = name;
    }

    public void receiveEvent(String event) {
        executor.submit(() -> {
            try {
                int sleepTime = ThreadLocalRandom.current().nextInt(1, 4) * 1000;
                Thread.sleep(sleepTime);
                System.out.println(name + " processed event: " + event + " (after " + sleepTime / 1000 + " sec)");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}

// 3️⃣ Client Code (Handles Input and Event Publishing)
public class PublisherSubscriberDemo {
    public static void main(String[] args) {
        EventPublisher publisher = new EventPublisher();

        EventSubscriber observer1 = new EventSubscriber("Observer-1");
        EventSubscriber observer2 = new EventSubscriber("Observer-2");
        EventSubscriber observer3 = new EventSubscriber("Observer-3");

        publisher.registerSubscriber(observer1);
        publisher.registerSubscriber(observer2);
        publisher.registerSubscriber(observer3);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message (type 'stop' to exit): ");
            String input = scanner.nextLine();

            if ("stop".equalsIgnoreCase(input)) {
                System.out.println("Stopping application...");
                break;
            }

            publisher.publishEvent(input);
        }

        scanner.close();
        System.exit(0);
    }
}
