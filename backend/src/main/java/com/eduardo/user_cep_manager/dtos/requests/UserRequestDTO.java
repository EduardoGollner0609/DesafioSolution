package com.eduardo.user_cep_manager.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequestDTO {

	@Size(min = 6, message = "O nome deve ter ao menos 6 caracteres")
	@NotBlank(message = "Campo requerido")
	private String name;
	@Size(min = 11, message = "O cpf deve ter 11 caracteres")
	@NotBlank(message = "Campo requerido")
	private String cpf;
	@Size(min = 8, message = "O cep deve ter ao menos 8 dígitos")
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
