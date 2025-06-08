package com.eduardo.user_cep_manager.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.eduardo.user_cep_manager.dtos.requests.UserRequestDTO;
import com.eduardo.user_cep_manager.dtos.responses.UserResponseDTO;
import com.eduardo.user_cep_manager.entitites.User;
import com.eduardo.user_cep_manager.gateways.abstractions.CepGateway;
import com.eduardo.user_cep_manager.repositories.UserRepository;
import com.eduardo.user_cep_manager.services.exceptions.ResourceNotFoundException;
import com.eduardo.user_cep_manager.utils.factory.Factory;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@Mock
	private UserRepository repository;

	@Mock
	private CepGateway cepGateway;

	@InjectMocks
	private UserService service;

	private User user;
	private User userWithId;
	private Long existingId;
	private Long nonExistingId;
	private String nonExistingCep;
	private UserRequestDTO requestDTO;

	@BeforeEach
	public void setup() throws Exception {
		existingId = 1L;
		nonExistingId = 1000L;
		user = Factory.createUser();
		userWithId = Factory.createUserWithId(existingId);
		requestDTO = new UserRequestDTO(user);
		nonExistingCep = "111111111";

		Mockito.when(repository.save(user)).thenReturn(userWithId);
		Mockito.when(cepGateway.findAddressByCep(user.getAddress().getCep()))
				.thenReturn(Optional.of(user.getAddress()));
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(userWithId));
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());
		Mockito.when(repository.findAll()).thenReturn(List.of(user));
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(user);
		Mockito.when(cepGateway.findAddressByCep(nonExistingCep)).thenReturn(Optional.empty());
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);
		Mockito.when(repository.existsById(existingId)).thenReturn(true);
		Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
	}

	@Test
	public void createShouldReturnUserWithIdAndCep() {
		UserResponseDTO responseDTO = service.create(requestDTO);

		Assertions.assertNotNull(responseDTO.id());
		Assertions.assertNotNull(responseDTO.address());
		Assertions.assertEquals(user.getAddress().getCep(), responseDTO.address().cep());
	}

	@Test
	public void findByIdShouldReturnUserByIdWhenIdExists() {
		UserResponseDTO responseDTO = service.findById(existingId);

		Assertions.assertEquals(existingId, responseDTO.id());
		Assertions.assertNotNull(responseDTO.id());
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> service.findById(nonExistingId));

		Assertions.assertEquals(String.format("Usuário do id %d não foi encontrado.", nonExistingId),
				exception.getMessage());
	}

	@Test
	public void findAllShouldReturnAllUsers() {
		List<UserResponseDTO> responseDTO = service.findAll();

		Assertions.assertNotEquals(List.of(), responseDTO);
	}

	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> service.update(nonExistingId, requestDTO));

		Assertions.assertEquals(String.format("Usuário do id %d não foi encontrado.", nonExistingId),
				exception.getMessage());
	}

	@Test
	public void updateShouldReturnUserUpdatedWhenIdExists() {
		UserResponseDTO responseDTO = service.update(existingId, requestDTO);

		Assertions.assertEquals(requestDTO.getName(), responseDTO.name());
		Assertions.assertEquals(requestDTO.getCpf(), responseDTO.cpf());
		Assertions.assertEquals(requestDTO.getCep(), responseDTO.address().cep());
	}

	@Test
	public void deleteByIdShouldThrowResourceNotFoundExceptionWhenIdNotExists() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
				() -> service.deleteById(nonExistingId));

		Assertions.assertEquals(String.format("Usuário do id %d não foi encontrado.", nonExistingId),
				exception.getMessage());
	}

	@Test
	public void deleteByIdShouldDoesNotThrowWhenIdExists() {
		Assertions.assertDoesNotThrow(() -> service.deleteById(existingId));
	}

	@Test
	public void updateAddressShouldUpdatedUserAddressWhenCepExists() {
		User newUser = new User();
		service.updateAddress(newUser, user.getAddress().getCep());

		Assertions.assertEquals(user.getAddress().getCep(), newUser.getAddress().getCep());
		Assertions.assertEquals(user.getAddress().getStreet(), newUser.getAddress().getStreet());
		Assertions.assertEquals(user.getAddress().getNeighborhood(), newUser.getAddress().getNeighborhood());
		Assertions.assertEquals(user.getAddress().getCity(), newUser.getAddress().getCity());
		Assertions.assertEquals(user.getAddress().getState(), newUser.getAddress().getState());
	}

	@Test
	public void updateAddressShouldThrowResourceNotFoundExceptionWhenCepNotExists() {
		ResourceNotFoundException exception = Assertions.assertThrows(ResourceNotFoundException.class,
				() -> service.updateAddress(user, nonExistingCep));
		Assertions.assertEquals(String.format("Cep %s não foi encontrado.", nonExistingCep), exception.getMessage());
	}

}