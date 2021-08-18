package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.UpdateBookDto;
import com.bridgelabz.bookstore.dto.UpdateCartDto;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.ICartService;
@RestController
public class CartController {
	
 @Autowired
 private ICartService cartService;

 @PostMapping("/addToCart/{token}")
	public ResponseEntity<Response> addBookToCart(@PathVariable  String token,@RequestBody UpdateCartDto cartDto) {
	Response response = cartService.addToCart(token,cartDto);
	return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
 @GetMapping("/getAllCartItems/{token}")
	public ResponseEntity<Response>getAllCartItem(@PathVariable  String token){
		Response response=cartService.getAllCartItem(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
 
 @PutMapping("/updateCartItem/{token}/{id}")
	public ResponseEntity<Response> updateCartItem(@PathVariable String token ,@PathVariable Long id, @RequestBody UpdateCartDto dto,BindingResult result) {
		Response respDTO = cartService.updateCartItem(token, id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCartItem/{token}/{id}")
	public ResponseEntity<Response> removeCartItem(@PathVariable String token,@PathVariable Long id) {
		cartService.removeCartItem(token, id);
		Response respDTO = new Response("Deleted Book Details with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
 
 
}
