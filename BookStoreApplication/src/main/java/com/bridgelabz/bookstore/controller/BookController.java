package com.bridgelabz.bookstore.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.UpdateBookDto;
import com.bridgelabz.bookstore.exception.InvalidBookDetailsException;
import com.bridgelabz.bookstore.response.Response;
import com.bridgelabz.bookstore.service.IBookService;

@RestController
public class BookController {
	@Autowired
	private IBookService bookService;
	@PostMapping("/addbook/{token}")
	public ResponseEntity<Response>createBook(@PathVariable  String token, @RequestBody BookDto bookDto,BindingResult result){
		Response response=bookService.createBook(token, bookDto);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	

	@GetMapping("/getallBookDetails/{token}")
	public ResponseEntity<Response> getBookDetails(@PathVariable String token) {
		Response respDTO = bookService.getAllBookDetails(token);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/getBook/{token}/{id}")
	public ResponseEntity<Response> getBookDetails(@PathVariable String token, @PathVariable Long id) {
		Response respDTO = bookService.getBookDetails(token, id);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	@PutMapping("/updatehiring/{token}/{id}")
	public ResponseEntity<Response> updateBookDetails(@PathVariable String token ,@PathVariable Long id, @RequestBody UpdateBookDto dto,BindingResult result) {
		Response respDTO = bookService.updateBookDetails(token, id, dto);
		System.out.println(respDTO);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	

	@GetMapping("/getcount/{token}")
	public ResponseEntity<Response> getbookCount(@PathVariable String token) {
		Response respDTO = bookService.getbookCount(token);
		return new ResponseEntity<Response>(respDTO, HttpStatus.OK);
	}
	
	
}
