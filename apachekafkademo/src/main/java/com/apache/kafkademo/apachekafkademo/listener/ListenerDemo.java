package com.apache.kafkademo.apachekafkademo.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ListenerDemo {
	 /*@Value("${spring.kafka.template.default-topic}")
	 String topicName;
	 @Value("${spring.kafka.consumer.group-id}")
	 String groupId;*/
	 @KafkaListener(topics= "assignment_four", groupId = "groupdemo") 
	  public void consume(String message)
	  { System.out.println("consumed message"+message); }
	  
}
