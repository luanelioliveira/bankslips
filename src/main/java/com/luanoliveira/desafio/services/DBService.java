package com.luanoliveira.desafio.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;

@Service
public class DBService {

	@Autowired
	BankSlipRepository repo;
	
	public void instantiateTestDatabase() {		
		BankSlip b1 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100), "GM Eletronics", "PENDING");
		BankSlip b2 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(500), "SpaceX", "PENDING");
		BankSlip b3 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(150), "AuthTech", "PENDING");
		BankSlip b4 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(300), "BitNEX", "PENDING");
		BankSlip b5 = new BankSlip(null, new Date(System.currentTimeMillis()), new BigDecimal(100), "HiTech", "PENDING");
		repo.save(Arrays.asList(b1,b2,b3,b4,b5));		
	}
	
}
