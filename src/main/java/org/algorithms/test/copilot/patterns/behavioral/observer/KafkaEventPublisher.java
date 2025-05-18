package org.algorithms.test.copilot.patterns.behavioral.observer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class KafkaEventPublisher {
    private final KafkaProducer<String, String> producer;
    private static final String TOPIC = "events";

    public KafkaEventPublisher() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092"); // Kafka broker
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    public void publishEvent(String event) {
        producer.send(new ProducerRecord<>(TOPIC, event));
        System.out.println("📢 Published event: " + event);
    }

    public void close() {
        producer.close();
    }
}
