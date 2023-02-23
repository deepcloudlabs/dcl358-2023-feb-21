package com.example.crm.dto.response;

import lombok.Getter;

@Getter
public class UpdateCustomerResponse {

	private final String status;

	public UpdateCustomerResponse(String status) {
		this.status = status;
	}

}
