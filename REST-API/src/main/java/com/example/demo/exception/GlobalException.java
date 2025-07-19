package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> globalExcep (Exception exception) {
		return new ResponseEntity<>("There is problem in backend"+exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
