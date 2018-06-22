package com.luanoliveira.desafio.util;

import org.springframework.stereotype.Component;

@Component
public class UUIDUtils {

	public boolean isValid(String uuid) {
		return uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"); 
	}
	
}
