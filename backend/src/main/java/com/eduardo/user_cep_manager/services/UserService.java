package com.eduardo.user_cep_manager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardo.user_cep_manager.dtos.requests.UserRequestDTO;
import com.eduardo.user_cep_manager.dtos.responses.UserResponseDTO;
import com.eduardo.user_cep_manager.entitites.Address;
import com.eduardo.user_cep_manager.entitites.User;
import com.eduardo.user_cep_manager.gateways.abstractions.CepGateway;
import com.eduardo.user_cep_manager.repositories.UserRepository;
import com.eduardo.user_cep_manager.services.exceptions.CpfExistsException;

@Service
public class UserService {

	private final UserRepository repository;
	private final CepGateway cepGateway;

	public UserService(UserRepository repository, CepGateway cepGateway) {
		this.repository = repository;
		this.cepGateway = cepGateway;
	}

	// Create
	public UserResponseDTO create(UserRequestDTO requestDTO) {
		validateCpf(requestDTO.cpf());
		User user = new User();
		copyDtoToEntity(user, requestDTO);
		updateAddress(user, requestDTO.cep());
		return new UserResponseDTO(repository.save(user));
	}

	// Read (FindAll)
	public List<UserResponseDTO> findAll() {
		List<User> users = repository.findAll();
		// Sintaxe Opcional para retornar com lambda: return users.stream().map(u -> new UserResponseDTO(u)).toList();
		return users.stream().map(UserResponseDTO::new).toList();

	}

	private void validateCpf(String cpf) {
		if (repository.existsByCpf(cpf)) {
			throw new CpfExistsException("Cpf já existe.");
		}
	}

	private void updateAddress(User user, String cep) {
		Address address = cepGateway.findAddressByCep(cep).orElseThrow(() -> {
			throw new ResourceNotFoundException(String.format("Cep %s não foi encontrado.", cep));
		});
		user.setAddress(address);
	}

	private void copyDtoToEntity(User user, UserRequestDTO requestDTO) {
		user.setName(requestDTO.name());
		user.setCpf(requestDTO.cpf());
	}

}
