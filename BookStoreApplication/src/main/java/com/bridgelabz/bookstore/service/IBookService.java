package com.bridgelabz.bookstore.service;

import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.UpdateBookDto;
import com.bridgelabz.bookstore.exception.InvalidBookDetailsException;
import com.bridgelabz.bookstore.response.Response;

@Service
public interface IBookService {
	public Response createBook(String token,BookDto bookDto)throws InvalidBookDetailsException;
	public Response getAllBookDetails(String token)throws InvalidBookDetailsException;
	public Response getBookDetails(String token,Long id)throws InvalidBookDetailsException;
	public Response updateBookDetails(String token,Long id,UpdateBookDto updateDto)throws InvalidBookDetailsException;
	public  Response getbookCount(String token)throws InvalidBookDetailsException;
	public Response deleteBookById(String token,Long id)throws InvalidBookDetailsException ;
}