package com.example.crm.dto.response;

import lombok.Getter;

@Getter
public class AcquireCustomerResponse {

	private final String status;

	public AcquireCustomerResponse(String status) {
		this.status = status;
	}

}
