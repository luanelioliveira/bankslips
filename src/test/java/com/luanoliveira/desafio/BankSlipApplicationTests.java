package com.luanoliveira.desafio;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankSlipApplicationTests {

	@Test
	public void contextLoads() {
		BigDecimal x = null;
		assertNull(x);
	}

}
