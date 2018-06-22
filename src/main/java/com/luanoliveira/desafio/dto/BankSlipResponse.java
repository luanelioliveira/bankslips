package com.luanoliveira.desafio.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.model.enuns.StatusBankSlip;
import com.luanoliveira.desafio.util.DateUtil;

public class BankSlipResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	DateUtil dateUtil;
	
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
		if (getStatus().equals(StatusBankSlip.PENDING.toString())) {
			return calcFine(getTotalInCents(), getDueDate());
		} else {
			return new BigDecimal(0);
		}
	}			

	public BigDecimal calcFine(BigDecimal value, Date dueDate) {
		
		long diff = DateUtil.getDifferenceDays(dueDate, new Date(System.currentTimeMillis()));
		Double fine = 0.0;
		
		if(diff > 0 && diff <= 10) {
			fine = calcRate(value.doubleValue(), 0.005) * diff;
		} else if (diff > 10) {
			fine = calcRate(value.doubleValue(), 0.01) * diff;
		}
		
		return new BigDecimal(fine);
		
	}
	
	public Double calcRate(Double value, Double rate) {
		return value * rate;
	}

}