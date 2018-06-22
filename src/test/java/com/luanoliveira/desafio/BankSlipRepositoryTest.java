package com.luanoliveira.desafio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.luanoliveira.desafio.repository.BankSlipRepository;

@RunWith(SpringRunner.class)
@WebMvcTest
public class BankSlipRepositoryTest {

	@Autowired
	MockMvc	mockMvc;
	
	@MockBean
	BankSlipRepository bankSlipRepository;
	
	@Test
	public void contextLoads() throws Exception {
		
		Mockito.when(bankSlipRepository.findAll());
		
	}
	
}
