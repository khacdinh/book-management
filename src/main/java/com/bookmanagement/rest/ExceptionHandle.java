package com.bookmanagement.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bookmanagement.util.ResponseBody;
import com.bookmanagement.util.ResponseStatus;

@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generateFailureResponseEntity() {
        ResponseStatus status = new ResponseStatus(101, "ERROR");
        ResponseBody body = new ResponseBody(status, "");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
