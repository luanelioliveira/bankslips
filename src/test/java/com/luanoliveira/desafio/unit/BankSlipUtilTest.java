package com.luanoliveira.desafio.unit;

import org.junit.Test;

import com.luanoliveira.desafio.util.BankSlipUtil;

import junit.framework.TestCase;

public class BankSlipUtilTest extends TestCase {

	@Test
	public void testValidStatus() {
		assertTrue(BankSlipUtil.isValidStatus("PENDING"));
		assertTrue(BankSlipUtil.isValidStatus("PAID"));
		assertTrue(BankSlipUtil.isValidStatus("CANCELED"));
	}
	
	@Test
	public void testInvalidStatus() {
		assertTrue(!BankSlipUtil.isValidStatus("TEST"));
		assertTrue(!BankSlipUtil.isValidStatus(""));
		assertTrue(!BankSlipUtil.isValidStatus(null));
	}

	@Test
	public void testValidTotalInCents() {
		assertTrue(BankSlipUtil.isValidTotalInCents(0.0));
		assertTrue(BankSlipUtil.isValidTotalInCents(1.0));
		assertTrue(BankSlipUtil.isValidTotalInCents(15.0));
		assertTrue(BankSlipUtil.isValidTotalInCents(1000.0));
	}

	@Test
	public void testInvalidTotalInCents() {
		assertTrue(!BankSlipUtil.isValidTotalInCents(-1.0));
	}
	
}
