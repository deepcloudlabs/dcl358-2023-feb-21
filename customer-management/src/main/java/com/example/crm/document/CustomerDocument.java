package com.example.crm.document;

import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "customers")
@Data
public class CustomerDocument {
	@Id
	private String identity;
	private String fullname;
	private String email;
	private String sms;
	private List<Address> addresses;

	public Optional<Address> addAddress(Address address) {
		var addressId = address.getId();
		var oldAddress= addresses.stream().filter(id -> id.equals(addressId)).findFirst(); 
		if (oldAddress.isPresent()) {
			addresses.remove(address);
		}
		addresses.add(address);
		return oldAddress;
	}

	public void removeAddress(Address address) {
		addresses.remove(address);
	}
}
