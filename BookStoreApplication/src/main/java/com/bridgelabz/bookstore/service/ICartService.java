package com.bridgelabz.bookstore.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.UpdateCartDto;
import com.bridgelabz.bookstore.response.Response;
@Service
public interface ICartService {
   public Response addToCart(String token, UpdateCartDto dto);
   public Response getAllCartItem(String token);
   public Response removeCartItem(String token, Long cartId);
   public Response updateCartItem(String token,Long id, UpdateCartDto cartDto);



}
