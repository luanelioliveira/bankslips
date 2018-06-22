package com.luanoliveira.desafio.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanoliveira.desafio.dto.BankSlipListResponse;
import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.dto.BankSlipResponse;
import com.luanoliveira.desafio.dto.BankSlipUpdate;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.services.BankSlipService;

@RestController
@RequestMapping(value="/rest/bankslips")
public class BankSlipController {

	@Autowired
	private BankSlipService service;

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody BankSlipRequest bankSlipRequest){
		BankSlip bankSlip = service.insert(bankSlipRequest);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(bankSlip.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<BankSlipListResponse>> list(){
		List<BankSlipListResponse> bankSlips = service.list();
		return ResponseEntity.ok().body(bankSlips) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BankSlipResponse> findById(@PathVariable String id){
		BankSlipResponse bankSlipResponse = service.getDetail(id);	
		return ResponseEntity.ok().body(bankSlipResponse) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BankSlip> update(@PathVariable String id, @RequestBody BankSlipUpdate bankSlipUpdate){
		BankSlip bankSlip = service.update(id, bankSlipUpdate);
		return ResponseEntity.ok().body(bankSlip);
	}
	
}