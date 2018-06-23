package com.luanoliveira.desafio.unit;

import static org.junit.Assert.assertNotEquals;

import java.util.Calendar;

import com.luanoliveira.desafio.util.DateUtil;

import junit.framework.TestCase;

public class DateUtilTest extends TestCase {
	
	public void testValidDateDiff() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DAY_OF_MONTH, 5);
		assertEquals(5, DateUtil.getDifferenceDays(c1.getTime(), c2.getTime()));
		
	}

public void testInvalidDateDiff() {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c2.add(Calendar.DAY_OF_MONTH, 5);
		assertNotEquals(5, DateUtil.getDifferenceDays(c2.getTime(), c1.getTime()));
		
	}
	
}
