package com.bridgelabz.bookstore.dto;

import lombok.Data;

@Data
public class UpdateCartDto {
  private long bookId;
  private int quantity;
}

