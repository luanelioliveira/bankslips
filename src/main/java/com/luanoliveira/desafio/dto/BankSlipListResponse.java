package com.luanoliveira.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luanoliveira.desafio.model.BankSlip;

public class BankSlipListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private String id;

	@JsonProperty("due_date")
	@JsonFormat(pattern="YYYY-MM-dd")
	private Date dueDate;
	
	@JsonProperty("total_in_cents")
	private BigDecimal totalInCents;
	
	@JsonProperty("customer")
	private String customer;
	
	public BankSlipListResponse() {
		
	}

	public BankSlipListResponse(BankSlip bankSlip) {
		this.id = bankSlip.getId();
		this.dueDate = bankSlip.getDueDate();
		this.totalInCents = bankSlip.getTotalInCents();
		this.customer = bankSlip.getCustomer();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public BigDecimal getTotalInCents() {
		return totalInCents;
	}

	public void setTotalInCents(BigDecimal totalInCents) {
		this.totalInCents = totalInCents;
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
}