package com.luanoliveira.desafio.process;

import java.util.Calendar;

import com.luanoliveira.desafio.util.DateUtil;

import junit.framework.TestCase;

public class ValidacaoDataTest extends TestCase {
	
	public void testDiferencaData() {
		
		Calendar c1 = Calendar.getInstance();
		
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DAY_OF_MONTH, 5);
		
		assertEquals(5, DateUtil.getDifferenceDays(c1.getTime(), c2.getTime()));
		
	}

}
