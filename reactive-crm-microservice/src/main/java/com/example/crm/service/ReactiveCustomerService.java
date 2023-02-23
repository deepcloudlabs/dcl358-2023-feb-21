package com.example.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.event.CustomerAcquiredEvent;
import com.example.crm.event.CustomerReleasedEvent;
import com.example.crm.repository.CustomerDocumentReactiveRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {
	private final CustomerDocumentReactiveRepository customerRepository;
	private final ReactiveKafkaProducerTemplate<String,String> kafkaTemplate;
	private final String topicName;
	private final ObjectMapper objectMapper;
	
	public ReactiveCustomerService(
			@Value("${topicName}") String topicName,
			ObjectMapper objectMapper,
			CustomerDocumentReactiveRepository customerRepository,
			ReactiveKafkaProducerTemplate<String, String> kafkaTemplate) {
		this.customerRepository = customerRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
		this.topicName = topicName;
	}

	public Mono<CustomerDocument> getCustomerByIdentity(String customerId) {
		return customerRepository.findById(customerId);
	}

	public Flux<CustomerDocument> getCustomers(int pageNo, int pageSize) {
		return customerRepository.findAll(PageRequest.of(pageNo, pageSize));
	}

	public Mono<AcquireCustomerResponse> acquireCustomer(CustomerDocument customer) {
		return customerRepository.insert(customer)
		                  .doOnNext(insertedCustomer ->{
		                	  try {
		                		  var event = new CustomerAcquiredEvent(insertedCustomer.getIdentity());
		                		  var eventAsJson = objectMapper.writeValueAsString(event);
		                		  kafkaTemplate.send(topicName, eventAsJson)
		                		               .subscribe( result -> System.out.println("Event is send to the kafka broker."));
		                	  }catch (Exception e) {}
		                  }).map(cust -> new AcquireCustomerResponse("success"));
	}

	public Mono<UpdateCustomerResponse> updateCustomer(CustomerDocument customer) {
		var customerId = customer.getIdentity();
		return customerRepository.findById(customerId )
                .doOnNext( foundCustomer -> customerRepository.save(customer).subscribe() )
                .map(updatedCustomer -> new UpdateCustomerResponse("success"));
	}

	public Mono<CustomerDocument> releaseCustomer(String customerId) {
		return customerRepository.findById(customerId)
				                 .doOnNext( customer -> {
				                	 customerRepository.delete(customer).subscribe(resultOfDelete ->{
					                	  try {
					                		  var event = new CustomerReleasedEvent(customer);
					                		  var eventAsJson = objectMapper.writeValueAsString(event);
					                		  kafkaTemplate.send(topicName, eventAsJson)
					                		               .subscribe( resultOfSend -> System.out.println("Event is send to the kafka broker."));
					                	  }catch (Exception e) {}				                		 
				                	 });				       
				                 });
	}

}
