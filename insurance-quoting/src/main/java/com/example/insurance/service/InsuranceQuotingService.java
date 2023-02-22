package com.example.insurance.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.crm.event.CrmBaseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class InsuranceQuotingService {
	private final ObjectMapper objectMapper;
	
	public InsuranceQuotingService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@KafkaListener(topics = "${topicName}", groupId = "insurance-quoting")
	public void listenCrmEventsFromKafka(String event) throws Exception {
		System.err.println("New event has arrived from kafka broker: %s".formatted(event));
		var crmEvent = objectMapper.readValue(event, CrmBaseEvent.class);
		System.err.println("New event has arrived from kafka broker: %s".formatted(crmEvent));
	}
	
	@RabbitListener(queues = "crmque")
	public void listenCrmEventsFromRabbitmq(String event) throws Exception {
		System.err.println("New event has arrived from rabbitmq: %s".formatted(event));
		var crmEvent = objectMapper.readValue(event, CrmBaseEvent.class);
		System.err.println("New event has arrived from rabbitmq: %s".formatted(crmEvent));
	}
}
