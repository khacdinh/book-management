package com.bookmanagement.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookmanagement.exception.MyResourceNotFoundException;
import com.bookmanagement.util.ResponseBody;
import com.bookmanagement.util.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler(MyResourceNotFoundException.class)
	public ResponseEntity<?> generateFailureInMyResourceNotFoundException() {
		ResponseStatus status = new ResponseStatus(456, "MyResourceNotFoundException");
		ResponseBody body = new ResponseBody(status, "Resource not found");
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
