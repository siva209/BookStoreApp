package com.bridgelabz.bookstore.service;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.exception.InvalidOrderDetailsException;
import com.bridgelabz.bookstore.response.Response;
@Service
public interface IOrderService {
  public Response placeOrder(String token, OrderDto orderDTO)throws InvalidOrderDetailsException;
  public Response getAllOrderDetails(String token)throws InvalidOrderDetailsException;
  public Response cancelOrderById(String token, Long id)throws InvalidOrderDetailsException;
  public Response getOrderById(String token,Long id)throws InvalidOrderDetailsException;
	

}
