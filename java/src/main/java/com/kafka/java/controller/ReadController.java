package com.kafka.java.controller;

import com.kafka.java.dto.response.JsonPlaceHolderResponse;
import com.kafka.java.service.ReadService;
import com.kafka.java.utils.constant.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ApiConfig.API + ApiConfig.READ)
public class ReadController {
    @Autowired
    private ReadService readService;

    @GetMapping("/get")
    public ResponseEntity<List<JsonPlaceHolderResponse>> readDataFromJsonPlaceHolder() {
        return new ResponseEntity<>(readService.readData(), HttpStatus.OK);
    }
}
