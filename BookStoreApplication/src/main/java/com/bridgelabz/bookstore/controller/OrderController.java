package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IOrderService;

@RestController
public class OrderController {
	@Autowired
	private IOrderService orderService;
	
	@PostMapping("/addOrderDetails/{token}")
	public ResponseEntity<Response> placeOrder(@PathVariable  String token, @RequestBody OrderDto orderDto,BindingResult result){
		Response response=orderService.placeOrder(token, orderDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@GetMapping("/getAllOrders/{token}")
	public ResponseEntity<Response>getAllOrderDetails(@PathVariable  String token){
		Response response=orderService.getAllOrderDetails(token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getOrder/{token}/{id}")
	public ResponseEntity<Response> getOrderById(@PathVariable String token, @PathVariable Long id) {
		Response respDTO = orderService.getOrderById(token, id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	@DeleteMapping("/deleteOrder/{token}/{id}")
	public ResponseEntity<Response> cancelOrderById(@PathVariable String token,@PathVariable Long id) {
		orderService.cancelOrderById(token, id);
		Response respDTO = new Response("Deleted Order Details with id : ", id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	

}
