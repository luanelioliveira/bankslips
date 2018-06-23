package com.luanoliveira.desafio.services.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.luanoliveira.desafio.dto.BankSlipRequest;
import com.luanoliveira.desafio.model.enuns.BankSlipStatus;
import com.luanoliveira.desafio.resources.exceptions.FieldMessage;
import com.luanoliveira.desafio.util.BankSlipUtil;

public class BankSlipInsertValidator implements ConstraintValidator<BankSlipInsert, BankSlipRequest> {
	
	@Override
	public void initialize(BankSlipInsert ann) {
	}

	@Override
	public boolean isValid(BankSlipRequest request, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (request.getDueDate() == null) {
			list.add(new FieldMessage("due_date", "Can not be null"));
		}
		
		if (request.getTotalInCents() == null) {
			list.add(new FieldMessage("total_in_cents", "Can not be null"));
		} else {
			if (!BankSlipUtil.isValidTotalInCents(request.getTotalInCents().doubleValue())) {
				list.add(new FieldMessage("total_in_cents", "Can not be negative"));
			}				
		}
		
		if (request.getCustomer() == null) {
			list.add(new FieldMessage("customer", "Can not be null"));
		}

		if (request.getStatus() == null) {
			list.add(new FieldMessage("status", "Can not be null"));
		} else {
			if (!BankSlipUtil.isValidStatus(request.getStatus())) {
				list.add(new FieldMessage("status", "Allowed values are " + Arrays.toString(BankSlipStatus.values())));
			} 			
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}