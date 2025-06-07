package com.eduardo.user_cep_manager.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.eduardo.user_cep_manager.entitites.User;
import com.eduardo.user_cep_manager.utils.factory.Factory;

@DataJpaTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	private String existingCpf;
	private String noExistingCpf;
	private User user;

	@BeforeEach
	public void setup() throws Exception {
		user = Factory.createUser();
		existingCpf = user.getCpf();
		noExistingCpf = "111111111111";

		userRepository.deleteAll();
		userRepository.save(user);
	}

	@Test
	public void existsByCpfShouldReturnTrueWhenCpfExists() {
		boolean value = userRepository.existsByCpf(existingCpf);

		Assertions.assertEquals(true, value);
		Assertions.assertTrue(value);
		Assertions.assertFalse(!value);
	}

	@Test
	public void existsByCpfShouldReturnFalseWhenCpfNotExists() {
		boolean value = userRepository.existsByCpf(noExistingCpf);

		Assertions.assertEquals(false, value);
		Assertions.assertFalse(value);
		Assertions.assertTrue(!value);
	}

	@Test
	public void findByCpfShouldReturnUserByCpfWhenCpfExists() {
		User value = userRepository.findByCpf(existingCpf);

		Assertions.assertNotNull(value);
		Assertions.assertEquals(user.getId(), value.getId());
		Assertions.assertEquals(user.getCpf(), value.getCpf());
	}

	@Test
	public void findByCpfShouldReturnNullWhenCpfNotExists() {
		User value = userRepository.findByCpf(noExistingCpf);

		Assertions.assertNull(value);
		Assertions.assertEquals(null, value);
	}
}
