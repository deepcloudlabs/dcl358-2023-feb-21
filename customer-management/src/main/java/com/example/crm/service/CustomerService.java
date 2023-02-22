package com.example.crm.service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.request.ChangeCustomerAddressResponse;
import com.example.crm.dto.response.AddCustomerResponse;
import com.example.crm.dto.response.ChangeCustomerAddressRequest;

public interface CustomerService {

	ChangeCustomerAddressResponse changeCustomerAddress(ChangeCustomerAddressRequest command, String customerId,
			String addressId);

	CustomerDocument getCustomerByIdentity(String customerId);

	AddCustomerResponse addCustomer(CustomerDocument customer);

}
