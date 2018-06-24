package com.luanoliveira.desafio.rest;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;

public class BankSlipRestGetTest extends BankSlipApplicationTests {

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
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		BankSlip b1 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100.0), "Conta Azul Teste 1", "PENDING");
		BankSlip b2 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100.0), "Conta Azul Teste 2", "PENDING");

		bankSlipList.add(bankSlipRepository.save(b1));
        bankSlipList.add(bankSlipRepository.save(b2));	
	
		this.bankSlipList = bankSlipRepository.findAll();
	}
	
	@Test
    public void testUUIDInvalidBankSlip() throws Exception {
        mockMvc.perform(get("/bankslips/9646785-d6de-484e-ae67-a4af0e18bec4"))
                .andExpect(status().isBadRequest());
    }
	
	@Test
    public void testNotFoundBankSlip() throws Exception {
        mockMvc.perform(get("/bankslips/55627bfa-4f9e-4dbc-bf1c-bc0c30de5591"))
                .andExpect(status().isNotFound());
    }
	
	
	@Test
    public void testReadDetailBankSlip() throws Exception {
        mockMvc.perform(get("/bankslips/"
                + this.bankSlipList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.bankSlipList.get(0).getId())))
                .andExpect(jsonPath("$.customer", is(this.bankSlipList.get(0).getCustomer())))
                .andExpect(jsonPath("$.status", is(this.bankSlipList.get(0).getStatus())));
    }
	
	@Test
    public void testReadBankSlips() throws Exception {
        mockMvc.perform(get("/bankslips/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

}
