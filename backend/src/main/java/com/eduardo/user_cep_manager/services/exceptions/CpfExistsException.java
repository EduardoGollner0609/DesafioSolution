package com.eduardo.user_cep_manager.services.exceptions;

public class CpfExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CpfExistsException(String msg) {
		super(msg);
	}

}
