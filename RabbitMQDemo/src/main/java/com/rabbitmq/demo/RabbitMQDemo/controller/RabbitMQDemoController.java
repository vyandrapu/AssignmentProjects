package com.rabbitmq.demo.RabbitMQDemo.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.rabbitmq.demo.RabbitMQDemo.Receiver;
import com.rabbitmq.demo.RabbitMQDemo.request.MessageRequest;
import com.rabbitmq.demo.RabbitMQDemo.request.MessageResponse;

@RestController
public class RabbitMQDemoController {
	@Autowired
	private  RabbitTemplate rabbitTemplate;
	@Autowired
	private  Receiver receiver;
	
	@Value("${spring.rabbitmq.template.exchange}")
	String exchangeName;
	@Value("${spring.rabbitmq.template.default-receive-queue}")
	String queueName;
	@Value("${spring.rabbitmq.template.routing-key}")
	String routingKey;
	@PostMapping(value="/rabbitmqdemo/addNewMessage", produces= {MediaType.APPLICATION_JSON_VALUE}, consumes= {MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public ResponseEntity<MessageResponse> addNewMessage(@RequestBody MessageRequest message) throws Exception {
		
		System.out.println("Enter into service call");
		Gson gson = new Gson();
		String jsonString = gson.toJson(message);
		MessageRequest msgData = gson.fromJson(jsonString, MessageRequest.class);
		System.out.println("Exchange Name :::"+exchangeName);
		System.out.println("Queue Name :::"+queueName);
		System.out.println("RoutingKey Name :::"+routingKey);
		System.out.println("msgData::: "+msgData);
		MessageResponse response = new MessageResponse();
		String name = message.getName();
		String email = message.getEmail();
		String city  = message.getAddresses().get(0).getCity();
		System.out.println("name ::::::::::"+name);
		System.out.println("email ::::::::::"+email);
		System.out.println("City ::::::::::"+city);
		System.out.println("Sending message...");
		//rabbitTemplate = new RabbitTemplate();
		//receiver = new Receiver();
		//rabbitTemplate.convertAndSend(exchangeName, routingKey, "welcome to Jalipudi");
		rabbitTemplate.convertAndSend(exchangeName, routingKey, msgData);
		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		response.setMessageResponse("Successfully completed request");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchangeName);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	}

	/*@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}*/

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
}
