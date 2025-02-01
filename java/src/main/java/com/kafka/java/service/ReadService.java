package com.kafka.java.service;

import com.kafka.java.dto.response.JsonPlaceHolderResponse;

import java.util.List;

public interface ReadService {
    List<JsonPlaceHolderResponse> readData();
}
