package com.example.crm.event;

import com.example.crm.document.CustomerDocument;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerReleasedEvent extends CrmBaseEvent {
	private CustomerDocument customer;
	
}
