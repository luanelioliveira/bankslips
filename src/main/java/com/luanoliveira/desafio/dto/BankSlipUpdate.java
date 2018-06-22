package com.luanoliveira.desafio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BankSlipUpdate implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "status can not be null")
	private String status;
	
	public BankSlipUpdate() {
		
	}

	public BankSlipUpdate(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}