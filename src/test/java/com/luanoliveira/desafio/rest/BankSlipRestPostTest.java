package com.luanoliveira.desafio.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.luanoliveira.desafio.BankSlipApplicationTests;
import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;

public class BankSlipRestPostTest extends BankSlipApplicationTests {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	
	private List<BankSlip> bankSlipList = new ArrayList<>();
	
	@Autowired
	BankSlipRepository bankSlipRepository;
	
	@Autowired 
	private WebApplicationContext webApplicationContext;
		
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)	.build();
		this.bankSlipList = bankSlipRepository.findAll();	
	}
	
	@Test
    public void testInvalidCreateBankSlip() throws Exception {
        mockMvc.perform(post("/bankslips")
                .content(json(new BankSlipRequest(null, null, null, null)))
                .contentType(contentType))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void testCreateBankSlip() throws Exception {
        String bankSlipJson = json(new BankSlip(
                null, new Date(System.currentTimeMillis()), new BigDecimal(100.0), "Conta Azul Teste 3", "PENDING"));

        this.mockMvc.perform(post("/bankslips")
                .contentType(contentType)
                .content(bankSlipJson))
                .andExpect(status().isCreated());
    }

}
