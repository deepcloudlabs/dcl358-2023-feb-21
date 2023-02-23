package com.example.crm.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CustomerAcquiredEvent extends CrmBaseEvent {
	private String identity;
}
