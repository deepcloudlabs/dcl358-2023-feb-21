package com.example.crm.dto.response;

import com.example.crm.document.Address;

import lombok.Data;

@Data
public class ChangeCustomerAddressRequest {
	private Address address;
}
