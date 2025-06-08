package com.eduardo.user_cep_manager.dtos.requests;

import com.eduardo.user_cep_manager.services.validations.UserInsertValid;

@UserInsertValid
public class UserInsertDTO extends UserRequestDTO {

	public UserInsertDTO(String name, String cpf, String cep) {
		super(name, cpf, cep);
	}

}
