package com.example.crm.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerAcquiredEvent extends CrmBaseEvent {
	private String identity;
}
