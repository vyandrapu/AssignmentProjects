package com.rabbitmq.consumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.consumer.listener.RabbitMQListener;
@Configuration
public class RabbitMQConfig {
	
	@Value("${spring.rabbitmq.queue}")
	String queueName;

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
	

		@Bean
		MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
			SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
			simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
			simpleMessageListenerContainer.setQueues(queue());
			simpleMessageListenerContainer.setMessageListener(new RabbitMQListener());
			return simpleMessageListenerContainer;

		}
		

}
