package com.eduardo.user_cep_manager.dtos.responses;

import com.eduardo.user_cep_manager.entitites.User;

public record UserResponseDTO(Long id, String name, String cpf, AddressResponseDTO address) {

	
	public UserResponseDTO(User user) {
		this(
				user.getId(),
				user.getName(),
				user.getCpf(),
				new AddressResponseDTO(user.getAddress())
				);
	}
}
