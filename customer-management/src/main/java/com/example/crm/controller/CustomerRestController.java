package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.crm.dto.request.ChangeCustomerAddressResponse;
import com.example.crm.dto.response.ChangeCustomerAddressRequest;
import com.example.crm.service.CustomerService;

@RestController
@RequestScope
@RequestMapping("customers")
@Validated
@CrossOrigin
public class CustomerRestController {

	private final CustomerService customerService;

	public CustomerRestController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PutMapping("{customerId}/addresses/{addressId}")
	public ChangeCustomerAddressResponse changeAddress(
			@RequestBody @Validated ChangeCustomerAddressRequest command,
			@PathVariable String customerId,String addressId) {
		return customerService.changeCustomerAddress(command,customerId,addressId);
	}
}
