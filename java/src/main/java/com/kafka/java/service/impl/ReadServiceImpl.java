package com.kafka.java.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.kafka.java.client.JsonPlaceHolderClient;
import com.kafka.java.dto.response.JsonPlaceHolderResponse;
import com.kafka.java.service.ReadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ReadServiceImpl implements ReadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadServiceImpl.class);

    private final JsonPlaceHolderClient jsonPlaceHolderClient;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReadServiceImpl(JsonPlaceHolderClient jsonPlaceHolderClient,
                           KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    /**
     * Reads data from JSONPlaceholder API and sends it to Kafka.
     */
    @Override
    public List<JsonPlaceHolderResponse> readData() {
        ResponseEntity<String> response = jsonPlaceHolderClient.getPosts();
        String responseBody = Optional.ofNullable(response.getBody()).orElse("");

        if (responseBody.isBlank()) {
            LOGGER.error("Response body is empty or null");
            return Collections.emptyList();
        }

        try {
            LOGGER.info("Response body received: {}", responseBody);

            // Deserialize JSON response
            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, JsonPlaceHolderResponse.class);
            List<JsonPlaceHolderResponse> res = objectMapper.readValue(responseBody, listType);

            // Send to Kafka if list is not empty
            if (!res.isEmpty()) {
                for(int i=0; i<res.size(); i++) {
                    JsonPlaceHolderResponse json = res.get(i);
                   // String message = "userId = "+ String.valueOf(json.getUserId()) +" id = "+ String.valueOf(json.getId()) +" title = "+ json.getTitle() +" body = "+ json.getBody();
                    String message = String.format("userId = %d, id = %d, title = %s, body = %s",
                            json.getUserId(), json.getId(), json.getTitle(), json.getBody());
                    kafkaTemplate.send("json-place-holder", "kafka-key", message);
                    LOGGER.info("Sent {} records to Kafka", message);
                }
            } else {
                LOGGER.warn("Received empty list from JSON response");
            }

            return res;

        } catch (IOException e) {
            LOGGER.error("Error parsing JSON response", e);
            throw new RuntimeException("Error parsing response", e);
        }
    }
}
