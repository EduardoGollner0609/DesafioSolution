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

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserInsertDTO requestDTO) {
		UserResponseDTO responseDTO = service.create(requestDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id())
				.toUri();
		return ResponseEntity.created(uri).body(responseDTO);
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long userId) {
		UserResponseDTO responseDTO = service.findById(userId);
		return ResponseEntity.ok(responseDTO);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll() {
		List<UserResponseDTO> responseDTO = service.findAll();
		return ResponseEntity.ok(responseDTO);
	}

	@PutMapping(value = "/{userId}")
	public ResponseEntity<UserResponseDTO> create(@PathVariable Long userId,
			@RequestBody @Valid UserUpdateDTO requestDTO) {
		UserResponseDTO responseDTO = service.update(userId, requestDTO);
		return ResponseEntity.ok(responseDTO);
	}

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<UserResponseDTO> create(@PathVariable Long userId) {
		service.deleteById(userId);
		return ResponseEntity.noContent().build();
	}
}
