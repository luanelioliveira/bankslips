package com.luanoliveira.desafio.unit;

import java.util.Calendar;

import com.luanoliveira.desafio.util.BankSlipCalc;

import junit.framework.TestCase;

public class BankSlipCalcTest extends TestCase {

	private Calendar c = Calendar.getInstance();
	
	public void testCalculaJurosVencimentoFuturo() {
		assertEquals(0.0, BankSlipCalc.calcFine(100.0, c.getTime()));
	}
	
	public void testCalculaJurosVencimentoHoje() {
		assertEquals(0.0, BankSlipCalc.calcFine(100.0, c.getTime()));
	}
	
	public void testCalculaJurosVencimento5DiasAtrasado() {
		c.add(Calendar.DAY_OF_MONTH, -5);		
		assertEquals(2.5, BankSlipCalc.calcFine(100.0, c.getTime()));
	}

	public void testCalculaJurosVencimento15DiasAtrasado() {
		c.add(Calendar.DAY_OF_MONTH, -15);		
		assertEquals(15.0, BankSlipCalc.calcFine(100.0, c.getTime()));
	}
	
	public void testCalculaJuros() {		
		assertEquals(200.0, BankSlipCalc.calcRate(100.0, 2.0));
	}
	
}
