package com.eduardo.user_cep_manager.dtos.requests;

import com.eduardo.user_cep_manager.services.validations.UserUpdateValid;

@UserUpdateValid
public class UserUpdateDTO extends UserRequestDTO {
	
	public UserUpdateDTO(String name, String cpf, String cep) {
		super(name, cpf, cep);
	}

}
