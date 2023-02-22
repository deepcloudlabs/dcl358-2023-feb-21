package com.example.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.crm.document.CustomerDocument;

public interface CustomerDocumentRepository extends MongoRepository<CustomerDocument, String>{
	Optional<CustomerDocument> findByEmail(String email);
	Optional<CustomerDocument> findBySms(String sms);
	List<CustomerDocument> findByAddressesCity(String city);
}
