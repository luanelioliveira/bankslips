package com.luanoliveira.desafio.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.model.enuns.StatusBankSlip;
import com.luanoliveira.desafio.resources.exceptions.FieldMessage;

public class BankSlipInsertValidator implements ConstraintValidator<BankSlipInsert, BankSlipRequest> {
	
	@Override
	public void initialize(BankSlipInsert ann) {
	}

	@Override
	public boolean isValid(BankSlipRequest request, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		

		if (request.getStatus() == null) {
			list.add(new FieldMessage("status", "Can not be null"));
		}
		
		try {
			StatusBankSlip status = StatusBankSlip.fromValue(request.getStatus());				
		} catch (IllegalArgumentException ex) {
			list.add(new FieldMessage("status", ex.getMessage()));
		}
		
		if (request.getTotalInCents().doubleValue() < 0) {
			list.add(new FieldMessage("total_in_cents", "Can not be negative"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}