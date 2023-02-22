package com.example.crm.dto.request;

import lombok.Data;

@Data
public class ChangeCustomerAddressResponse {

	private String status;
	private String message;

	public ChangeCustomerAddressResponse(String status) {
		this.status = status;
	}

	public ChangeCustomerAddressResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

}
