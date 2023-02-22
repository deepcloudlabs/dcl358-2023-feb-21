package com.example.crm.service.business;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.event.AddressChangedEvent;
import com.example.crm.repository.CrmEventRepository;
import com.example.crm.service.EventPublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class EventPublisherKafkaService implements EventPublisherService {
	private final KafkaTemplate<String,String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;
	private final CrmEventRepository crmEventRepository;
	
	public EventPublisherKafkaService(
			@Value("${topicName}") String topicName,
			ObjectMapper objectMapper,
			CrmEventRepository crmEventRepository,
			KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
		this.topicName = topicName;
		this.objectMapper = objectMapper;
		this.crmEventRepository = crmEventRepository;
	}

	@Override
	@Retry(name = "eventRetry", fallbackMethod = "emitFallback")
	public void emit(AddressChangedEvent event) throws IOException {
		kafkaTemplate.send(topicName, objectMapper.writeValueAsString(event));
	}

	public void emitFallback(AddressChangedEvent event,Throwable e) throws IOException {
		crmEventRepository.insert(event);
	}
}
