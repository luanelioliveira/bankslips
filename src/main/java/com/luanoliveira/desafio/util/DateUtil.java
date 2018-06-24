package com.luanoliveira.desafio.util;

import java.util.Date;

public class DateUtil {

	private DateUtil() {
		
	}
	public static long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return diff / 24 / 60 / 60 / 1000;
	}
	
}
