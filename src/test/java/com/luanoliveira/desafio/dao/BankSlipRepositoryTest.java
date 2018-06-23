package com.luanoliveira.desafio.dao;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.luanoliveira.desafio.BankSlipApplication;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankSlipRepositoryTest {
	
	@Autowired
    private BankSlipRepository bankSlipRepository;
	
	@Test
	@Transactional
	@Rollback(true)
	public void testSaveBankSlip() {			
		BankSlip bankSlip = new BankSlip();
		bankSlip.setId(null);
		bankSlip.setDueDate(new Date(System.currentTimeMillis()));
		bankSlip.setTotalInCents(new BigDecimal(100));
		bankSlip.setCustomer("GM Eletronics");
		bankSlip.setStatus("PENDING");		
		bankSlip = bankSlipRepository.save(bankSlip);
		assertNotNull(bankSlip.getId());
	}
	
}
