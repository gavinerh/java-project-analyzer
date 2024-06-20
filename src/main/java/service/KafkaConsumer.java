package service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "email-publish",groupId = "group-id")
    public void listen(String message) {
        System.out.println("Kafka msg: " + message);
    }

}
