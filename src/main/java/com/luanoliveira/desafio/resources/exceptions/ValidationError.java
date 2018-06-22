package com.luanoliveira.desafio.resources.exceptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError implements Serializable {
	private static final long serialVersionUID = 1L;	
	
	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidationError(String status, String message) {
		super(status, message);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}


}
