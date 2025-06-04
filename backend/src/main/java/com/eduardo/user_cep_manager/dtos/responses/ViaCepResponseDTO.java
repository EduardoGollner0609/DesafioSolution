package com.eduardo.user_cep_manager.dtos.responses;

public record ViaCepResponseDTO(String cep, String logradouro, String bairro, String localidade, String uf,
		Boolean erro) {

}
