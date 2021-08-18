package com.bridgelabz.bookstore.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.configuration.AppConfig;
import com.bridgelabz.bookstore.dto.UpdateCartDto;
import com.bridgelabz.bookstore.exception.InvalidBookDetailsException;
import com.bridgelabz.bookstore.exception.InvalidOrderDetailsException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.CartDetails;
import com.bridgelabz.bookstore.model.OrderDetails;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.response.Response;
@Service
public class CartServiceImpl implements ICartService{
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CartRepository cartRepository;
	  
	@Override
	public Response addToCart(String token, UpdateCartDto cartDto) {
		CartDetails   verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, CartDetails.class); 
		System.out.println("Value="+verify);
		if(verify != null) {
			CartDetails addCartDetails=modelmapper.map(cartDto, CartDetails.class);
			addCartDetails.setQuantity(cartDto.getQuantity());
			cartRepository.save(addCartDetails);
	       return new Response(AppConfig.getMessageAccessor().getMessage("11"),addCartDetails,201,"true");}
	else {
		throw new InvalidOrderDetailsException(AppConfig.getMessageAccessor().getMessage("111"),null,400,"true");
	}
		
	}
	@Override
	public Response removeCartItem(String token, Long id) {
		CartDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, CartDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<CartDetails> isUserPresent = cartRepository.findById(id);
			if (isUserPresent.isPresent()) {
				cartRepository.deleteById(id);
				return new Response(AppConfig.getMessageAccessor().getMessage("12"),isUserPresent,200,"true");
			} else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("112"),null,400,"true");
			}
		}
			else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("112"),null,400,"true");
			}
		}
	


	@Override
	public Response updateCartItem(String token, Long id,UpdateCartDto dto) {
		CartDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, CartDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<CartDetails> isUserPresent = cartRepository.findById(id);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setQuantity(dto.getQuantity());
				isUserPresent.get().setBookId(dto.getBookId());
				isUserPresent.get().setUserId(dto.getBookId());
				cartRepository.save(isUserPresent.get());
            	return new Response(AppConfig.getMessageAccessor().getMessage("13"),isUserPresent,200,"true");
            } else {

            	throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("113"),null,400,"true");
            }
            }else
            {
            	throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("113"),null,400,"true");
            }
            }
	@Override
	public Response getAllCartItem(String token) {
		CartDetails verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, CartDetails.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			List<CartDetails> isUserPresent = cartRepository.findAll();
			return new Response(AppConfig.getMessageAccessor().getMessage("14"),isUserPresent,200,"true");
			}
			else {
				throw new InvalidOrderDetailsException(AppConfig.getMessageAccessor().getMessage("114"),null,400,"true");
			}
	}
	}



