package com.eduardo.user_cep_manager.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.eduardo.user_cep_manager.dtos.requests.UserInsertDTO;
import com.eduardo.user_cep_manager.dtos.requests.UserUpdateDTO;
import com.eduardo.user_cep_manager.dtos.responses.UserResponseDTO;
import com.eduardo.user_cep_manager.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
@Tag(name = "Users", description = "Controller for Users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@Operation(description = "Criar usuário", summary = "Criar usuário", responses = {
			@ApiResponse(description = "Criado", responseCode = "201"),
			@ApiResponse(description = "Entidade não pode ser processado por causa das validações", responseCode = "422"),
			@ApiResponse(description = "Cep não encontrado", responseCode = "404") })
	@PostMapping(produces = "application/json")
	public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserInsertDTO requestDTO) {
		UserResponseDTO responseDTO = service.create(requestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id())
				.toUri();
		return ResponseEntity.created(uri).body(responseDTO);
	}

	@Operation(description = "Buscar usuário pelo id", summary = "Buscar usuário pelo id", responses = {
			@ApiResponse(description = "Ok", responseCode = "200"),
			@ApiResponse(description = "Usuário não encontrado", responseCode = "404") })
	@GetMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long userId) {
		UserResponseDTO responseDTO = service.findById(userId);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "Buscar todos usuários", summary = "Buscar todos usuários", responses = {
			@ApiResponse(description = "Ok", responseCode = "200") })
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		List<UserResponseDTO> responseDTO = service.findAll();
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "Atualizar usuário", summary = "Atualizar usuário", responses = {
			@ApiResponse(description = "Ok", responseCode = "200"),
			@ApiResponse(description = "Entidade não pode ser processado por causa das validações", responseCode = "422"),
			@ApiResponse(description = "Cep não encontrado", responseCode = "404"),
			@ApiResponse(description = "Usuário não encontrado", responseCode = "404") })
	@PutMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<UserResponseDTO> update(@PathVariable Long userId,
			@RequestBody @Valid UserUpdateDTO requestDTO) {
		UserResponseDTO responseDTO = service.update(userId, requestDTO);
		return ResponseEntity.ok(responseDTO);
	}

	@Operation(description = "Atualizar usuário", summary = "Atualizar usuário", responses = {
			@ApiResponse(description = "Sem retorno", responseCode = "204"),
			@ApiResponse(description = "Usuário não encontrado", responseCode = "404") })
	@DeleteMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<UserResponseDTO> deleteById(@PathVariable Long userId) {
		service.deleteById(userId);
		return ResponseEntity.noContent().build();
	}
}
