package com.devsuperior.bds02.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.bds02.services.exceptions.DatabaseException;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardErrorController> entityNotFound(ResourceNotFoundException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardErrorController errorController = new StandardErrorController();
		errorController.setTimestamp(Instant.now());
		errorController.setStatus(status.value()); 
		errorController.setError("Resource not found"); 
		errorController.setMessage(e.getMessage());
		errorController.setPath(req.getRequestURI());
		
		return ResponseEntity.status(status).body(errorController);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardErrorController> database(DatabaseException e, HttpServletRequest req) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardErrorController errorController = new StandardErrorController();
		errorController.setTimestamp(Instant.now());
		errorController.setStatus(status.value());
		errorController.setError("Database integration exception"); 
		errorController.setMessage(e.getMessage());
		errorController.setPath(req.getRequestURI());
		
		return ResponseEntity.status(status).body(errorController);
	}
}
