package com.bridgelabz.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="book_details")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long Id;
	private String BookName;
	private String BookAuthor;
	private String Bookdescription;
	private String BookLogo;
	private double BookPrice;
	private int BookQuantity;
}
