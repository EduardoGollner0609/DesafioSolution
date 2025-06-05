package com.eduardo.user_cep_manager.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {

	@NotBlank(message = "Campo requerido")
	private String name;
	@NotBlank(message = "Campo requerido")
	private String cpf;
	@NotBlank(message = "Campo requerido")
	private String cep;

	public UserRequestDTO() {
	}

	public UserRequestDTO(String name, String cpf, String cep) {
		this.name = name;
		this.cpf = cpf;
		this.cep = cep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
