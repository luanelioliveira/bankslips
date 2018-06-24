package com.luanoliveira.desafio.services.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.luanoliveira.desafio.dto.StatusRequest;
import com.luanoliveira.desafio.model.enuns.BankSlipStatus;
import com.luanoliveira.desafio.resources.exceptions.FieldMessage;
import com.luanoliveira.desafio.util.BankSlipUtil;

public class BankSlipUpdateValidator implements ConstraintValidator<BankSlipUpdate, StatusRequest> {
	
	@Override
	public void initialize(BankSlipUpdate ann) {}

	@Override
	public boolean isValid(StatusRequest status, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();

		if (status.getStatus() == null) {
			list.add(new FieldMessage("status", "Can not be null"));
		}
		
		if (!BankSlipUtil.isValidStatus(status.getStatus())) {
			list.add(new FieldMessage("status", "Allowed values are " + Arrays.toString(BankSlipStatus.values())));
		} 
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}