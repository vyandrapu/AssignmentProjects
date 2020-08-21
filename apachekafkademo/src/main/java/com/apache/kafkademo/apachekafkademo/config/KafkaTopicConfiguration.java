package com.apache.kafkademo.apachekafkademo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfiguration {
	
	
	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String,Object> config = new HashMap<>();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		return new KafkaAdmin(config);
	}
	@Value("${spring.kafka.template.default-topic}")
	String topicName;
	@Bean
	  public NewTopic topicExample() {
	    return TopicBuilder.name(topicName)
	      .partitions(3)
	      .replicas(1)
	      .build();
	  }

}
