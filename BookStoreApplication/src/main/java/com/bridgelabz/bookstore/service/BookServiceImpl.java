package com.bridgelabz.bookstore.service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.bookstore.configuration.AppConfig;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.UpdateBookDto;
import com.bridgelabz.bookstore.exception.InvalidBookDetailsException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.response.Response;

@Service
public class BookServiceImpl implements IBookService{
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private BookRepository bookRepo;
	
   
	@Override
	public Response createBook(String token,BookDto bookDto) {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Book AddDetails = modelmapper.map(bookDto, Book.class);
		System.out.println(AddDetails);
		bookRepo.save(AddDetails);
		return new Response(AppConfig.getMessageAccessor().getMessage("1"),AddDetails,201,"true");
		
		}
		else {
			throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("101"),null,400,"true");
		}
			
		}

	@Override
	public Response getAllBookDetails(String token) throws InvalidBookDetailsException {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			List<Book> isUserPresent = bookRepo.findAll();
			return new Response(AppConfig.getMessageAccessor().getMessage("2"),isUserPresent,200,"true");
			}
			else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("102"),null,400,"true");
			}
			
		}


	@Override
	public Response getBookDetails(String token, Long id) throws InvalidBookDetailsException {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<Book> isUserPresent = bookRepo.findById(id);
			Book candidates = isUserPresent.get();
			return new Response(AppConfig.getMessageAccessor().getMessage("3"),isUserPresent,200,"true");
		}
		else {
			throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("103"),null,400,"true");
		}
		}

	@Override
	public Response updateBookDetails(String token, Long id, UpdateBookDto updateDto)
			throws InvalidBookDetailsException {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<Book> isUserPresent = bookRepo.findById(id);
			if (isUserPresent.isPresent()) {
				isUserPresent.get().setBookAuthor(updateDto.getBookAuthor());
				isUserPresent.get().setBookName(updateDto.getBookName());
				isUserPresent.get().setBookPrice(updateDto.getBookPrice());
				isUserPresent.get().setBookQuantity(updateDto.getBookQuantity());
				isUserPresent.get().setBookdescription(updateDto.getBookdescription());
                bookRepo.save(isUserPresent.get());
            	return new Response(AppConfig.getMessageAccessor().getMessage("4"),isUserPresent,200,"true");
            } else {

            	throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("104"),null,400,"true");
            }
            }else
            {
            	throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("104"),null,400,"true");
            }
            }

	@Override
	public Response getbookCount(String token) throws InvalidBookDetailsException {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			List<Book> isUserPresent = bookRepo.findAll();
			long count = 0;
			for (Iterator iterator = isUserPresent.iterator(); iterator.hasNext();) {
				Book lmsHiring = (Book) iterator.next();
				count++;
			}
			return new Response(AppConfig.getMessageAccessor().getMessage("5"),count,200,"true");
		}
	else {
		throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("105"),null,400,"true");
	}
	}


	@Override
	public Response deleteBookById(String token, Long id) throws InvalidBookDetailsException {
		Book verify = restTemplate.getForObject("http://localhost:9091/verifyemail/"+token, Book.class);
		System.out.println("Value="+verify);
		if(verify != null) {
			Optional<Book> isUserPresent = bookRepo.findById(id);
			if (isUserPresent.isPresent()) {
				bookRepo.deleteById(id);
				return new Response(AppConfig.getMessageAccessor().getMessage("6"),isUserPresent,200,"true");
			} else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("106"),null,400,"true");
			}
		}
			else {
				throw new InvalidBookDetailsException(AppConfig.getMessageAccessor().getMessage("106"),null,400,"true");
			}
		}
	}

	