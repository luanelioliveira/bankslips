package com.luanoliveira.desafio.services.exceptions;

public class UUIDNotValidException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UUIDNotValidException(String msg) {
		super(msg);
	}
	
	public UUIDNotValidException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
