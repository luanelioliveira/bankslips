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
	
	private static final String MESSAGE_NOT_NULL = "This is a required field";
	private static final String MESSAGE_NOT_NEGATIVE = "Can not be negative value";

	@Override
	public void initialize(BankSlipInsert ann) {
	
	}

	@Override
	public boolean isValid(BankSlipRequest request, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (request.getDueDate() == null) {
			list.add(new FieldMessage("due_date", MESSAGE_NOT_NULL));
		}
		
		if (request.getTotalInCents() == null) {
			list.add(new FieldMessage("total_in_cents", MESSAGE_NOT_NULL));
		} else {
			if (!BankSlipUtil.isValidTotalInCents(request.getTotalInCents().doubleValue())) {
				list.add(new FieldMessage("total_in_cents", MESSAGE_NOT_NEGATIVE));
			}				
		}
		
		if (request.getCustomer() == null || request.getCustomer().equals("")) {
			list.add(new FieldMessage("customer", MESSAGE_NOT_NULL));
		}

		if (request.getStatus() == null || request.getStatus().equals("")) {
			list.add(new FieldMessage("status", MESSAGE_NOT_NULL));
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