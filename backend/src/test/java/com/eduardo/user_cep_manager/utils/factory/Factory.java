package com.eduardo.user_cep_manager.utils.factory;

import com.eduardo.user_cep_manager.dtos.requests.UserRequestDTO;
import com.eduardo.user_cep_manager.entitites.Address;
import com.eduardo.user_cep_manager.entitites.User;

public class Factory {

	public static User createUser() {
		return new User(null, "Eduardo", "199999999",
				new Address("29072320", "Rua Henrique Martins Tuche", "Segurança do Lar", "Vitória", "Espirito Santo"));
	}

	public static User createUserWithId(Long id) {
		return new User(id, "Eduardo", "199999999",
				new Address("29072320", "Rua Henrique Martins Tuche", "Segurança do Lar", "Vitória", "Espirito Santo"));
	}

	public static UserRequestDTO createRequestDTO() {
		return new UserRequestDTO("Eduardo", "199999999", "29072320");
	}
}
