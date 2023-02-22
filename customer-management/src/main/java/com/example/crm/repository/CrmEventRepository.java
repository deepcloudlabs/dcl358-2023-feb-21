package com.example.crm.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.event.CrmBaseEvent;

public interface CrmEventRepository extends MongoRepository<CrmBaseEvent,String> {

}
