package com.eduardo.user_cep_manager.gateways.exceptions;

public class GatewayException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GatewayException(String msg) {
		super(msg);
	}

}
