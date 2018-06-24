package com.luanoliveira.desafio.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.luanoliveira.desafio.dto.StatusRequest;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.services.BankSlipService;

@RestController
@RequestMapping(value="/bankslips")
public class BankSlipController {

	@Autowired
	private BankSlipService service;

	@GetMapping
	public ResponseEntity<List<BankSlipListResponse>> list(){
		List<BankSlipListResponse> bankSlips = service.list();
		return ResponseEntity.ok().body(bankSlips) ;
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody BankSlipRequest bankSlipRequest){
		BankSlip bankSlip = service.insert(bankSlipRequest);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(bankSlip.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BankSlipResponse> findById(@PathVariable String id){
		BankSlipResponse bankSlipResponse = service.getDetail(id);	
		return ResponseEntity.ok().body(bankSlipResponse) ;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BankSlip> update(@PathVariable String id, @Valid @RequestBody StatusRequest status){
		BankSlip bankSlip = service.update(id, status);
		return ResponseEntity.ok().body(bankSlip);
	}
	
}
