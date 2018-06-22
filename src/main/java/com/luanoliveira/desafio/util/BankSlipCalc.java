package com.luanoliveira.desafio.util;

import java.util.Date;

public class BankSlipCalc {

	public static Double calcFine(Double value, Date dueDate) {
		
		Double fine = 0.0;
		long diff = DateUtil.getDifferenceDays(dueDate, new Date(System.currentTimeMillis()));
		
		if(diff > 0 && diff <= 10) {
			fine = calcRate(value, 0.005) * diff;
		} else if (diff > 10) {
			fine = calcRate(value, 0.01) * diff;
		}
		
		return fine;
		
	}
	
	public static Double calcRate(Double value, Double rate) {
		return value * rate;
	}

	
}
