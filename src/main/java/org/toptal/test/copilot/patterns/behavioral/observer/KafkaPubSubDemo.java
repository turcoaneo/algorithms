package org.toptal.test.copilot.patterns.behavioral.observer;

import java.util.Scanner;

public class KafkaPubSubDemo {
    public static void main(String[] args) {

        KafkaEventPublisher publisher = new KafkaEventPublisher();

        // Start subscribers in separate threads
        new Thread(() -> new KafkaEventSubscriber("Observer-1").listen()).start();
        new Thread(() -> new KafkaEventSubscriber("Observer-2").listen()).start();
        new Thread(() -> new KafkaEventSubscriber("Observer-3").listen()).start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter message (type 'stop' to exit): ");
            String input = scanner.nextLine();

            if ("stop".equalsIgnoreCase(input)) {
                System.out.println("Stopping application...");
                publisher.close();
                break;
            }

            publisher.publishEvent(input);
        }

        scanner.close();
        System.exit(0);
    }
}
