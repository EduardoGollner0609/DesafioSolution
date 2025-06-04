package com.eduardo.user_cep_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardo.user_cep_manager.entitites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByCpf(String cpf);

}
