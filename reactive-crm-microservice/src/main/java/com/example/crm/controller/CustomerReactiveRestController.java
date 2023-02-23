package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.response.AcquireCustomerResponse;
import com.example.crm.dto.response.UpdateCustomerResponse;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("customers")
@Validated
@CrossOrigin
public class CustomerReactiveRestController {

	private final ReactiveCustomerService customerService;

	public CustomerReactiveRestController(ReactiveCustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("{customerId}")
	public Mono<CustomerDocument> getCustomer(@PathVariable String customerId) {
		return customerService.getCustomerByIdentity(customerId);
	}

	@GetMapping(params = { "pageNo", "pageSize" })
	public Flux<CustomerDocument> getCustomers(@RequestParam int pageNo, @RequestParam int pageSize) {
		return customerService.getCustomers(pageNo, pageSize);
	}

	@PostMapping
	public Mono<AcquireCustomerResponse> acquireCustomer(@RequestBody CustomerDocument customer) {
		return customerService.acquireCustomer(customer);
	}

	@PutMapping("{customerId}")
	public Mono<UpdateCustomerResponse> updateCustomer(@RequestBody CustomerDocument customer) {
		return customerService.updateCustomer(customer);
	}

	@DeleteMapping("{customerId}")
	public Mono<CustomerDocument> releaseCustomer(@PathVariable String customerId) {
		return customerService.releaseCustomer(customerId);
	}
}
