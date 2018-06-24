package com.luanoliveira.desafio;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BankSlipApplication.class)
@WebAppConfiguration
public class BankSlipApplicationTests {

	@Autowired
	BankSlipRepository bankSlipRepository;
	
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	@Test
	public void contextLoads() {
		
	}
	
	@Before
	public void setup() {
		
		this.bankSlipRepository.deleteAllInBatch();
		
		BankSlip b1 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100.0), "Conta Azul Teste 1", "PENDING");
		BankSlip b2 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100.0), "Conta Azul Teste 2", "PENDING");

		List<BankSlip> bankSlipList = new ArrayList<>();
		bankSlipList.add(bankSlipRepository.save(b1));
        bankSlipList.add(bankSlipRepository.save(b2));	
	
	}

	@Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
	
}
