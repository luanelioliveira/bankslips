package com.luanoliveira.desafio.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luanoliveira.desafio.dto.BankSlipListResponse;
import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.dto.BankSlipResponse;
import com.luanoliveira.desafio.dto.StatusRequest;
import com.luanoliveira.desafio.model.BankSlip;
import com.luanoliveira.desafio.resources.exceptions.StandardError;
import com.luanoliveira.desafio.resources.exceptions.ValidationError;
import com.luanoliveira.desafio.services.BankSlipService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(value = "bankslips")
@RestController
@RequestMapping(value="/bankslips")
public class BankSlipController {

	@Autowired
	private BankSlipService service;

	@ApiOperation(
			value = "List all bankslips", 
			produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(
					code=200,
					message="Return all bankslips",
					response = BankSlipListResponse.class)
	})
	@GetMapping
	public ResponseEntity<List<BankSlipListResponse>> list(){
		List<BankSlipListResponse> bankSlips = service.list();
		return ResponseEntity.ok().body(bankSlips) ;
	}
	
	@ApiOperation(
			value = "Create new bankslip",
			consumes = "application/json", 
			produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(
					code=201,
					message="Return bankslip created",
					response = BankSlip.class, 
					responseHeaders=
						@ResponseHeader(
								name="Location", 
								description="uri the new bankslip",
								response=String.class)),
			@ApiResponse(
					code=400, 
					message="Bankslip not provided in the request body",
					response=StandardError.class),
			@ApiResponse(
					code=422, 
					message="Invalid bankslip provided.The possible reasons are: A field of the provided bankslip was null or with invalid values",
					response=ValidationError.class)
	})
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<BankSlip> insert(@Valid @RequestBody BankSlipRequest bankSlipRequest){
		BankSlip bankSlip = service.insert(bankSlipRequest);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(bankSlip.getId()).toUri();
		
		return ResponseEntity.status(HttpStatus.CREATED).location(uri).body(bankSlip);
	}
	
	@ApiOperation(
			value = "Get bankslip by id", 
			produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(
					code=200,
					message="Return detail bankslip",
					response = BankSlipResponse.class),
			@ApiResponse(
					code=400, 
					message="Invalid id provided - it must be a valid UUID",
					response=StandardError.class),
			@ApiResponse(
					code=404, 
					message="Bankslip not found with the specified id",
					response=StandardError.class)
	})
	@GetMapping("/{id}")
	public ResponseEntity<BankSlipResponse> findById(@PathVariable String id){
		BankSlipResponse bankSlipResponse = service.getDetail(id);	
		return ResponseEntity.ok().body(bankSlipResponse) ;
	}
	
	@ApiOperation(
			value = "Update status bankslip",
			consumes = "application/json",
			produces = "application/json")
	@ApiResponses(value = {
			@ApiResponse(
					code=200,
					message="Bankslip Canceled/Paid",
					response = BankSlip.class),
			@ApiResponse(
					code=400, 
					message="Invalid id provided - it must be a valid UUID",
					response=StandardError.class),
			@ApiResponse(
					code=404, 
					message="Bankslip not found with the specified id",
					response=StandardError.class)
	})
	@PutMapping("/{id}")
	public ResponseEntity<BankSlip> update(@PathVariable String id, @Valid @RequestBody StatusRequest status){
		BankSlip bankSlip = service.update(id, status);
		return ResponseEntity.ok().body(bankSlip);
	}
	
}
