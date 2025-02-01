package com.kafka.java.kafka;

import com.kafka.java.dto.response.JsonPlaceHolderResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "json-place-holder", groupId = "my-group")
    public void listen(List<JsonPlaceHolderResponse> record) {
        System.out.println("Received Message: " + record);
    }
}
