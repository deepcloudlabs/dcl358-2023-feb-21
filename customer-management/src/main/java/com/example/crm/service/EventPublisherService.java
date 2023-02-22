package com.example.crm.service;

import java.io.IOException;

import com.example.crm.event.CrmBaseEvent;

public interface EventPublisherService {

	void emit(CrmBaseEvent event) throws IOException;

}
