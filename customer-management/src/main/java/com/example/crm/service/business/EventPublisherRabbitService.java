package com.example.crm.service.business;

import java.io.IOException;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.example.crm.event.CrmBaseEvent;
import com.example.crm.repository.CrmEventRepository;
import com.example.crm.service.EventPublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name="messagingSystem", havingValue = "rabbitmq")
public class EventPublisherRabbitService implements EventPublisherService {
	private final RabbitTemplate rabbitTemplate;
	private final String exchangeName;
	private final ObjectMapper objectMapper;
	private final CrmEventRepository crmEventRepository;
	
	public EventPublisherRabbitService(
			@Value("${exchangeName}") String exchangeName,
			ObjectMapper objectMapper,
			CrmEventRepository crmEventRepository,
			RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.exchangeName = exchangeName;
		this.objectMapper = objectMapper;
		this.crmEventRepository = crmEventRepository;
	}

	@Override
	public void emit(CrmBaseEvent event) throws IOException {
		rabbitTemplate.convertAndSend(exchangeName, null,objectMapper.writeValueAsString(event));			
	}

	public void emitFallback(CrmBaseEvent event,Throwable e) throws IOException {
		crmEventRepository.insert(event);
	}
}
