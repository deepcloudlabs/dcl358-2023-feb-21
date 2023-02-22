package com.example.crm.event;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.crm.document.Address;

import lombok.Data;

@Data
@Document(collection="crmevents")
public class AddressChangedEvent {
	@Id
	private String id= UUID.randomUUID().toString();
	private Address oldAddress;
	private Address newAddress;
}
