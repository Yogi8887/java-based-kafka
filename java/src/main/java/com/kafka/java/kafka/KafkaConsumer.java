package com.kafka.java.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class KafkaConsumer {

    @KafkaListener(topics = "json-place-holder", groupId = "my-group")
    public void listen(String record) {
        System.out.println("Received Message: " + record);
    }
}
