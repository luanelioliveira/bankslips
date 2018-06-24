package com.luanoliveira.desafio.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UUIDUtil {

	private UUIDUtil() {
		
	}
	
	public static boolean isValid(String uuidInStr) {
		if (uuidInStr == null) return false;
		try {
			UUID uuid = UUID.fromString(uuidInStr);
			return (uuidInStr.equals(uuid.toString()));
		} catch (IllegalArgumentException ex) {
			return false;
		} 
	}
	
}
