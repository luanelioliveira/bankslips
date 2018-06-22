package com.luanoliveira.desafio.model.enuns;

import java.util.Arrays;

public enum StatusBankSlip {

	PENDING("PENDING"),
	CANCELED("CANCELED"),
	PAID("PAID");
	
	private String value;

	private StatusBankSlip(String value) {
		this.value = value;
	}

	public static StatusBankSlip fromValue(String value) {
		for (StatusBankSlip status : values()) {
			if (status.value.equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException(
				"Allowed values are " + Arrays.toString(values()));
	}
}
