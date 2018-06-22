package com.luanoliveira.desafio.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanoliveira.desafio.dto.BankSlipListResponse;
import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.dto.BankSlipResponse;
import com.luanoliveira.desafio.dto.BankSlipUpdate;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.model.enuns.StatusBankSlip;
import com.luanoliveira.desafio.repository.BankSlipRepository;
import com.luanoliveira.desafio.services.exceptions.ObjectNotFoundException;
import com.luanoliveira.desafio.services.exceptions.UUIDNotValidException;
import com.luanoliveira.desafio.util.UUIDUtils;

@Service
public class BankSlipService {

	@Autowired
	private BankSlipRepository repo;
	
	@Autowired
	private UUIDUtils uuidUtil;
	
	public BankSlip insert(BankSlipRequest req) {
		BankSlip bankSlip = new BankSlip();
		bankSlip.setId(null);
		bankSlip.setDueDate(req.getDueDate());
		bankSlip.setTotalInCents(req.getTotalInCents());
		bankSlip.setCustomer(req.getCustomer());
		bankSlip.setStatus(req.getStatus());
		return repo.save(bankSlip);
	}

	public BankSlip update(String id, BankSlipUpdate bankSlipUpdate) {
		BankSlip bankSlip = findById(id);
		
		StatusBankSlip status = StatusBankSlip.fromValue(bankSlipUpdate.getStatus());
		
		bankSlip.setStatus(status.toString());
		return repo.save(bankSlip);
		
	}

	public BankSlipResponse getDetail(String id) {
		BankSlip bankSlip = findById(id);
		return new BankSlipResponse(bankSlip);
	}
	
	public List<BankSlipListResponse> list() {
		List<BankSlip> bankSlips = repo.findAll();
		List<BankSlipListResponse> response = bankSlips.stream()
				.map(obj -> new BankSlipListResponse(obj)).collect(Collectors.toList());
		return response;
	}
	
	public BankSlip findById(String id) {
		
		if(!uuidUtil.isValid(id)) {
			throw new UUIDNotValidException("Invalid id provided - it must be a valid UUID");
		}
		
		BankSlip bankSlip = repo.findOne(id);
		if(bankSlip == null) {
			throw new ObjectNotFoundException("Bankslip not found with the specified id");
		}
		
		return bankSlip;
	}
	
}
