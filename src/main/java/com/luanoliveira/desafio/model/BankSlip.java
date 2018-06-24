package com.luanoliveira.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class BankSlip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(generator = "uuid2")
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
	
	public BankSlip() {
	}
	
	public BankSlip(String id, Date dueDate, BigDecimal totalInCents, String customer, String status) {
		this.id = id;
		this.dueDate = dueDate;
		this.totalInCents = totalInCents;
		this.customer = customer;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}

	public void setId(final String id) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((totalInCents == null) ? 0 : totalInCents.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankSlip other = (BankSlip) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else {
			if (!customer.equals(other.customer)) 
				return false;
		}
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else {
			if (!dueDate.equals(other.dueDate)) 
				return false;
		}
		if (id == null) {
			if (other.id != null)
				return false;
		} else { 
			if (!id.equals(other.id))
				return false;
		}
		if (status == null) {
			if (other.status != null)
				return false;
		} else {
			if (!status.equals(other.status))
				return false;
		}
		if (totalInCents == null) {
			if (other.totalInCents != null)
				return false;
		} else { 
			if (!totalInCents.equals(other.totalInCents))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankSlip [id=");
		builder.append(id);
		builder.append(", dueDate=");
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
