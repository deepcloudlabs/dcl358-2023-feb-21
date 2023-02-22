package com.example.crm.service.business;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.crm.repository.CrmEventRepository;

@Service
@ConditionalOnProperty(name = "enableOutboxService", havingValue = "true")
public class OutboxService {
	private final CrmEventRepository crmEventRepository;
	

	public OutboxService(CrmEventRepository crmEventRepository) {
		this.crmEventRepository = crmEventRepository;
	}


	@Scheduled(fixedRate = 5_000)
	public void sendFailedEvents() {
		crmEventRepository.findAll()
		                  .forEach( event -> {
		                	  
		                  });
	}
}
