package com.bridgelabz.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.configuration.AppConfig;
import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.exception.InvalidBookDetailsException;
import com.bridgelabz.bookstore.exception.InvalidOrderDetailsException;
//import com.bridgelabz.bookstore.exception.InvalidOrderDetailsException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.OrderDetails;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserBookRepository;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.util.Jms;
import com.bridgelabz.bookstore.util.JwtToken;
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private JwtToken jwt;
	@Autowired
	private Jms jms;
	
	@Autowired
	private UserBookRepository userbookrepo;

	@Override
	public Response placeOrder(String token, OrderDto orderDTO) {
		OrderDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, OrderDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
		OrderDetails addOrderDetails=modelmapper.map(orderDTO, OrderDetails.class);
		addOrderDetails.setOrderDate(LocalDate.now());
		addOrderDetails.setQuantity(orderDTO.getQuantity());
		orderRepository.save(addOrderDetails);
        return new Response(AppConfig.getMessageAccessor().getMessage("7"),addOrderDetails,201,"true");
		
		}
		else {
			throw new InvalidOrderDetailsException(AppConfig.getMessageAccessor().getMessage("107"),null,400,"true");
		}
			
		}
	
	@Override
	public Response getAllOrderDetails(String token) throws InvalidOrderDetailsException {
		OrderDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, OrderDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			List<OrderDetails> isUserPresent = orderRepository.findAll();
			return new Response(AppConfig.getMessageAccessor().getMessage("8"),isUserPresent,200,"true");
			}
			else {
				throw new InvalidOrderDetailsException(AppConfig.getMessageAccessor().getMessage("108"),null,400,"true");
			}
	}
	
	

	@Override
	public Response getOrderById(String token, Long id) throws InvalidBookDetailsException {
		OrderDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, OrderDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<OrderDetails> isUserPresent = orderRepository.findById(id);
			OrderDetails isOrderDetails = isUserPresent.get();
			return new Response(AppConfig.getMessageAccessor().getMessage("9"),isUserPresent,200,"true");
		}
		else {
			throw new InvalidOrderDetailsException(AppConfig.getMessageAccessor().getMessage("109"),null,400,"true");
		}
		}	
	
	
	@Override
	public Response cancelOrderById(String token, Long id) throws InvalidOrderDetailsException {
		OrderDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, OrderDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<OrderDetails> isUserPresent = orderRepository.findById(id);
			if (isUserPresent.isPresent()) {
				bookRepo.deleteById(id);
				return new Response(AppConfig.getMessageAccessor().getMessage("10"),isUserPresent,200,"true");
			} else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("110"),null,400,"true");
			}
		}
			else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("110"),null,400,"true");
			}
		}
	}


	