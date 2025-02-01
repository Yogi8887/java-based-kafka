package com.kafka.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class JavaBasedKafkaApplication {
	public static void main(String[] args) {
		SpringApplication.run(JavaBasedKafkaApplication.class, args);
	}

}
