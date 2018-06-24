package com.luanoliveira.desafio.model.enuns;

public enum BankSlipStatus {

	PENDING("PENDING"),
	CANCELED("CANCELED"),
	PAID("PAID");
	
	private String status;

	private BankSlipStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public static BankSlipStatus toEnum(String status) {
		
		if(status == null) {
			return null;
		}
		
		for(BankSlipStatus x : BankSlipStatus.values()) {
			if(status.equals(x.getStatus())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Status inválido: " + status);
		
	}

}
