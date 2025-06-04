package com.eduardo.user_cep_manager.dtos.requests;

import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank(message = "Campo requerido") String name,
		@NotBlank(message = "Campo requerido") String cpf, @NotBlank(message = "Campo requerido") String cep) {

}
