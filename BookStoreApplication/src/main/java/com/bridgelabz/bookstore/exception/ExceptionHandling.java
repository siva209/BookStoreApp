package com.bridgelabz.bookstore.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling{
	
	@ExceptionHandler(InvalidOrderDetailsException.class)
	public ResponseEntity<ExceptionResponse> userCustomExceptions(InvalidOrderDetailsException ex)
	{
		ExceptionResponse res=new ExceptionResponse();
		res.setMessage(ex.getMessage());
		res.setData(ex.getData());
		res.setStatus(ex.getStatus());
		res.setStatusMsg(ex.getStatusMsg());
		return ResponseEntity.status(res.getStatus()).body(new ExceptionResponse(res.getMessage(),res.getData(),res.getStatus(),res.getStatusMsg()));
	}

}
