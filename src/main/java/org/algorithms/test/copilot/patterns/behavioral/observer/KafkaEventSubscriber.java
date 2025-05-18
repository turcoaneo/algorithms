package org.algorithms.test.copilot.patterns.behavioral.observer;

import org.apache.kafka.clients.consumer.*;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class KafkaEventSubscriber {
    private final KafkaConsumer<String, String> consumer;

    public KafkaEventSubscriber(String subscriberName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("group.id", subscriberName); // Unique group for each subscriber
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("events"));
    }

    public void listen() {
        //noinspection InfiniteLoopStatement
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                int sleepTime = ThreadLocalRandom.current().nextInt(1, 4) * 1000;
                try {
                    //noinspection BusyWait
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName() + " processed event: " + record.value() + " (after " + sleepTime / 1000 + " sec)");
            }
        }
    }
}
