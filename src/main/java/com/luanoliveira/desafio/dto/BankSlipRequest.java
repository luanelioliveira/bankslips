package com.luanoliveira.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luanoliveira.desafio.services.validation.BankSlipInsert;

@BankSlipInsert
public class BankSlipRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("due_date")
	private Date dueDate;
	
	@JsonProperty("total_in_cents")
	private BigDecimal totalInCents;
	
	@JsonProperty("customer")
	private String customer;
	
	@JsonProperty("status")
	private String status;
	
	public BankSlipRequest() {
		
	}

	public BankSlipRequest(Date dueDate, BigDecimal totalInCents, String customer, String status) {
		this.dueDate = dueDate;
		this.totalInCents = totalInCents;
		this.customer = customer;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankSlipCreateDTO [dueDate=");
		builder.append(dueDate);
		builder.append(", totalInCents=");
		builder.append(totalInCents);
		builder.append(", customer=");
		builder.append(customer);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
}
