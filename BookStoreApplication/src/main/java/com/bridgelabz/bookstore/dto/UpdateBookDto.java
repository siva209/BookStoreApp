package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class UpdateBookDto {
	private String BookName;
	private String BookAuthor;
	private String Bookdescription;
	private String BookLogo;
	private double BookPrice;
	private int BookQuantity;

}
