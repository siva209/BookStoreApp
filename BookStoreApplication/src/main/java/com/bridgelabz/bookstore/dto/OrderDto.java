package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class OrderDto {
	private int quantity;
	private double price;
	private String address;
	private long bookId;
}
