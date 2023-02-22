package com.example.crm.event;

import com.example.crm.dto.Address;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddressChangedEvent extends CrmBaseEvent {
	private Address oldAddress;
	private Address newAddress;
}
