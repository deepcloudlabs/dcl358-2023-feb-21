package com.example.crm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.event.AddressChangedEvent;

public interface CrmEventRepository extends MongoRepository<AddressChangedEvent,String> {

}
