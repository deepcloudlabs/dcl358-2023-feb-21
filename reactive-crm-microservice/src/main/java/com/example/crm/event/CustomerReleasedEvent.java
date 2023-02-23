package com.example.crm.event;

import com.example.crm.document.CustomerDocument;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CustomerReleasedEvent extends CrmBaseEvent {
	private CustomerDocument customer;

}
