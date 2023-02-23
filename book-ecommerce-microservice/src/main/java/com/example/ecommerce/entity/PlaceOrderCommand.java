package com.example.ecommerce.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;

@Data
@Entity
public class PlaceOrderCommand extends OrderCommand {
	private int userId;
	@OneToMany
	private List<BookItem> items;
}
