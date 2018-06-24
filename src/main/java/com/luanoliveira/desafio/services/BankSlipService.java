package com.luanoliveira.desafio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.desafio.dto.BankSlipListResponse;
import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.dto.BankSlipResponse;
import com.luanoliveira.desafio.dto.StatusRequest;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;
import com.luanoliveira.desafio.services.exceptions.ObjectNotFoundException;
import com.luanoliveira.desafio.services.exceptions.UUIDNotValidException;
import com.luanoliveira.desafio.util.UUIDUtil;

@Service
public class BankSlipService {

	@Autowired
	private BankSlipRepository repo;
	
	public BankSlip insert(BankSlipRequest request) {
		return repo.save(fromRequest(request));
	}

	public BankSlip update(String id, StatusRequest request) {
		return repo.save(fromRequest(id, request));	
	}

	public BankSlipResponse getDetail(String id) {
		BankSlip bankSlip = findById(id);
		return new BankSlipResponse(bankSlip);
	}
	
	public List<BankSlipListResponse> list() {
		List<BankSlip> bankSlips = repo.findAll();
		return bankSlips.stream()
				.map(map -> new BankSlipListResponse(map))
				.collect(Collectors.toList());
	}
	
	public BankSlip findById(String id) {
		
		if(!UUIDUtil.isValid(id)) {
			throw new UUIDNotValidException("Invalid id provided - it must be a valid UUID");
		}
		
		BankSlip bankSlip = repo.findOne(id);
		if(bankSlip == null) {
			throw new ObjectNotFoundException("Bankslip not found with the specified id");
		}
		
		return bankSlip;
	}
	
	public BankSlip fromRequest(BankSlipRequest request) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setId(null);
		bankSlip.setDueDate(request.getDueDate());
		bankSlip.setTotalInCents(request.getTotalInCents());
		bankSlip.setCustomer(request.getCustomer());
		bankSlip.setStatus(request.getStatus());
		return bankSlip;
	}
	
	public BankSlip fromRequest(String id, StatusRequest request) {
		BankSlip bankSlip = findById(id);
		bankSlip.setStatus(request.getStatus());
		return bankSlip;
	}
	
}
