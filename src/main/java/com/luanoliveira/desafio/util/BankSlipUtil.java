package com.luanoliveira.desafio.util;

import com.luanoliveira.desafio.model.enuns.BankSlipStatus;

public class BankSlipUtil {

	private BankSlipUtil() {
		
	}
	
	public static Boolean isValidStatus(String value) {
		for (BankSlipStatus status : BankSlipStatus.values()) {
			if (status.getStatus().equals(value)) {
				return true;
			}
		}
		return false;
	}
	
	public static Boolean isValidTotalInCents(Double value) {
		return (value >= 0);
	}

}
