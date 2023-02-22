package com.example.crm.service;

import java.io.IOException;

import com.example.crm.event.AddressChangedEvent;

public interface EventPublisherService {

	void emit(AddressChangedEvent event) throws IOException;

}
