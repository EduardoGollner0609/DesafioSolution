package com.eduardo.user_cep_manager.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eduardo.user_cep_manager.dtos.requests.UserRequestDTO;
import com.eduardo.user_cep_manager.dtos.responses.UserResponseDTO;
import com.eduardo.user_cep_manager.entitites.Address;
import com.eduardo.user_cep_manager.entitites.User;
import com.eduardo.user_cep_manager.gateways.abstractions.CepGateway;
import com.eduardo.user_cep_manager.repositories.UserRepository;
import com.eduardo.user_cep_manager.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

	private final UserRepository repository;
	private final CepGateway cepGateway;

	public UserService(UserRepository repository, CepGateway cepGateway) {
		this.repository = repository;
		this.cepGateway = cepGateway;
	}

	// Create
	@Transactional
	public UserResponseDTO create(UserRequestDTO requestDTO) {
		User user = new User();
		copyDtoToEntity(user, requestDTO);
		updateAddress(user, requestDTO.getCep());
		return new UserResponseDTO(repository.save(user));
	}

	// Read(FindById)
	public UserResponseDTO findById(Long userId) {
		User user = repository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Usuário do id %d não foi encontrado.", userId)));
		return new UserResponseDTO(user);
	}

	// Read (FindAll)
	@Transactional(readOnly = true)
	public List<UserResponseDTO> findAll() {
		List<User> users = repository.findAll();
		return users.stream().map(UserResponseDTO::new).toList();
	}

	// Update
	@Transactional
	public UserResponseDTO update(Long userId, UserRequestDTO requestDTO) {
		try {
			User user = repository.getReferenceById(userId);
			if (!user.getAddress().getCep().equals(requestDTO.getCep())) {
				updateAddress(user, requestDTO.getCep());
			}
			copyDtoToEntity(user, requestDTO);
			return new UserResponseDTO(repository.save(user));
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(String.format("Usuário do id %d não foi encontrado.", userId));
		}
	}

	// Delete
	@Transactional
	public void deleteById(Long userId) {
		if (!repository.existsById(userId)) {
			throw new ResourceNotFoundException(String.format("Usuário do id %d não foi encontrado.", userId));
		}
		repository.deleteById(userId);
	}

	void updateAddress(User user, String cep) {
		Address address = cepGateway.findAddressByCep(cep).orElseThrow(() -> {
			throw new ResourceNotFoundException(String.format("Cep %s não foi encontrado.", cep));
		});
		user.setAddress(address);
	}

	private void copyDtoToEntity(User user, UserRequestDTO requestDTO) {
		user.setName(requestDTO.getName());
		user.setCpf(requestDTO.getCpf());
	}

}