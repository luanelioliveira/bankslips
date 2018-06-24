package com.luanoliveira.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.model.enuns.BankSlipStatus;
import com.luanoliveira.desafio.util.BankSlipCalc;

public class BankSlipResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private String id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonProperty("due_date")
	private Date dueDate;
	
	@JsonProperty("total_in_cents")
	private BigDecimal totalInCents;
	
	@JsonProperty("customer")
	private String customer;
	
	@JsonProperty("status")
	private String status;
	
	public BankSlipResponse() {
		
	}

	public BankSlipResponse(BankSlip bankSlip) {
		this.id = bankSlip.getId();
		this.dueDate = bankSlip.getDueDate();
		this.totalInCents = bankSlip.getTotalInCents();
		this.customer = bankSlip.getCustomer();
		this.status = bankSlip.getStatus();
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	@JsonProperty("fine")
	public BigDecimal getFine() {
		if (getStatus().equals(BankSlipStatus.PENDING.toString())) {
			return BigDecimal.valueOf(BankSlipCalc.calcFine(getTotalInCents().doubleValue(), getDueDate()));
		} else {
			return BigDecimal.valueOf(0);
		}
	}			

}