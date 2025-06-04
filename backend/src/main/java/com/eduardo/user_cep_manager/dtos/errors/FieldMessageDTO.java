package com.eduardo.user_cep_manager.dtos.errors;

public class FieldMessageDTO {

	private String fieldName;
	private String message;

	public FieldMessageDTO(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getMessage() {
		return message;
	}
}
