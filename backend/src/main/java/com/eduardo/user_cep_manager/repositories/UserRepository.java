package com.eduardo.user_cep_manager.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.user_cep_manager.entitites.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByCpf(String cpf);

}
