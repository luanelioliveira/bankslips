package com.luanoliveira.desafio.unit;

import org.junit.Test;

import com.luanoliveira.desafio.util.UUIDUtil;

import junit.framework.TestCase;

public class UUIDUtilTest extends TestCase {
	
	@Test
	public void testValidUiid() {
		assertTrue(UUIDUtil.isValid("96467852-d6de-484e-ae67-a4af0e18bec4"));
	}
	
	@Test
	public void testInvalidUiid() {
		assertTrue(!UUIDUtil.isValid("96467852-d6de-484e-ae67-a4af0e18bec"));
		assertTrue(!UUIDUtil.isValid("96467852-d6de-484e-ae6-a4af0e18bec4"));
		assertTrue(!UUIDUtil.isValid("96467852-d6de-484-ae67-a4af0e18bec4"));
		assertTrue(!UUIDUtil.isValid("96467852-d6d-484e-ae67-a4af0e18bec4"));
		assertTrue(!UUIDUtil.isValid("9646785-d6de-484e-ae67-a4af0e18bec4"));
		assertTrue(!UUIDUtil.isValid(""));
		assertTrue(!UUIDUtil.isValid(null));
	}
}
