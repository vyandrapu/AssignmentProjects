package com.apache.kafkademo.apachekafkademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaDemoController {
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Value("${spring.kafka.template.default-topic}")
	private String topicName;
	@GetMapping("/publish/{message}")
	public String publishMessage(@PathVariable("message")  String message) {
		kafkaTemplate.send(topicName, message);
		return "message published successfully";
	}
}
