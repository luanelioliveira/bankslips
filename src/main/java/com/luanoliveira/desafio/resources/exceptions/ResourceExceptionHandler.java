package com.luanoliveira.desafio.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.luanoliveira.desafio.services.exceptions.DataIntegrityException;
import com.luanoliveira.desafio.services.exceptions.ObjectNotFoundException;
import com.luanoliveira.desafio.services.exceptions.UUIDNotValidException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.name(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.UNPROCESSABLE_ENTITY.name(), e.getMessage());
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest req){
		
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.name(), "Invalid bankslip provided. The possible reasons are:");
		
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<StandardError> IllegalState(IllegalStateException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<StandardError> HttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.METHOD_NOT_ALLOWED.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(err);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> MethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(UUIDNotValidException.class)
	public ResponseEntity<StandardError> UUIDNotValid(UUIDNotValidException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<StandardError> MediaTypeNotSupported(HttpMediaTypeNotSupportedException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> IllegalArgument(IllegalArgumentException e, HttpServletRequest req){
		
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}	

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> HttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest req){
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.name(), e.getMessage());	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}	
	
	
}
