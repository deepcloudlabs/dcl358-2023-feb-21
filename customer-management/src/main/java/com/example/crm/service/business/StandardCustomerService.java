package com.example.crm.service.business;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.crm.document.Address;
import com.example.crm.document.CustomerDocument;
import com.example.crm.dto.request.ChangeCustomerAddressResponse;
import com.example.crm.dto.response.ChangeCustomerAddressRequest;
import com.example.crm.event.AddressChangedEvent;
import com.example.crm.repository.CustomerDocumentRepository;
import com.example.crm.service.CustomerService;
import com.example.crm.service.EventPublisherService;

@Service
public class StandardCustomerService implements CustomerService {
	private final CustomerDocumentRepository customerDocumentRepository;
	private final EventPublisherService eventPublisherService;
	
	public StandardCustomerService(CustomerDocumentRepository customerDocumentRepository,
			EventPublisherService eventPublisherService) {
		this.customerDocumentRepository = customerDocumentRepository;
		this.eventPublisherService = eventPublisherService;
	}

	@Override
	public ChangeCustomerAddressResponse changeCustomerAddress(ChangeCustomerAddressRequest command, String customerId,
			String addressId) {
		try {
			CustomerDocument customer = customerDocumentRepository.findById(customerId)
					.orElseThrow(()-> new IllegalArgumentException("Cannot find customer"));
			var newAddress = command.getAddress();
			var oldAddress = customer.addAddress(newAddress);
			customerDocumentRepository.save(customer);
			var event = new AddressChangedEvent();
			if (oldAddress.isPresent())
				event.setOldAddress(oldAddress.get());
			event.setNewAddress(newAddress);
			eventPublisherService.emit(event);
			return new ChangeCustomerAddressResponse("success");
		} catch (IOException e) {
			e.printStackTrace();
			return new ChangeCustomerAddressResponse("failure",e.getMessage());
		}
	}

}
