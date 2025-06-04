package com.eduardo.user_cep_manager.gateways.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.eduardo.user_cep_manager.dtos.responses.ViaCepResponseDTO;
import com.eduardo.user_cep_manager.entitites.Address;
import com.eduardo.user_cep_manager.gateways.abstractions.CepGateway;
import com.eduardo.user_cep_manager.gateways.exceptions.GatewayException;

public class ViaCepGateway implements CepGateway {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Optional<Address> findAddressByCep(String cep) {
		try {
			String url = String.format("https://viacep.com.br/ws/%s/json/", cep);

			ResponseEntity<ViaCepResponseDTO> response = restTemplate.getForEntity(url, ViaCepResponseDTO.class);
			ViaCepResponseDTO responseDTO = response.getBody();

			if (response.getStatusCode().is2xxSuccessful() && responseDTO != null) {

				if (Boolean.TRUE.equals(responseDTO.erro())) {
					return Optional.empty();
				}

				Address address = new Address();
				copyDtoToEntity(address, response.getBody());
				return Optional.of(address);
			}

		} catch (Exception e) {
			switch (e) {
			 case HttpClientErrorException error -> {
                 throw new GatewayException("Erro de cliente ao consultar ViaCEP: " + error.getMessage());
             }
             case ResourceAccessException error -> {
                 throw new GatewayException("Erro de rede ao consultar ViaCEP: " + error.getMessage());
             }
             case RestClientException error -> {
                 throw new GatewayException("Erro na comunicação com ViaCEP: " + error.getMessage());
             }
             default -> {
                 throw new GatewayException("Erro inesperado ao consultar ViaCEP: " + e.getMessage());
             }
			}
		}
		return Optional.empty();
	}

	private void copyDtoToEntity(Address address, ViaCepResponseDTO response) {
		address.setCep(response.cep());
		address.setStreet(response.logradouro());
		address.setNeighborhood(response.bairro());
		address.setCity(response.localidade());
		address.setState(response.uf());
	}
}
