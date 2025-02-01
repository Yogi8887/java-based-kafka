package com.kafka.java.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name = "json-placeholder",url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceHolderClient {

    @GetMapping("/posts")
    ResponseEntity<String> getPosts();
}
