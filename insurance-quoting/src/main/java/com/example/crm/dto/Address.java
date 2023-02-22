package com.example.crm.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
public class Address {
	private String id;
	private String line;
	private String city;
	private String country;
	private String zipCode;
}
