package com.luanoliveira.desafio.dto;

import java.io.Serializable;

import com.luanoliveira.desafio.services.validation.BankSlipUpdate;

@BankSlipUpdate
public class StatusRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String status;
	
	public StatusRequest() {
		
	}

	public StatusRequest(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	
	
}