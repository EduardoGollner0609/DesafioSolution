package com.eduardo.user_cep_manager.services.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.eduardo.user_cep_manager.dtos.errors.FieldMessageDTO;
import com.eduardo.user_cep_manager.dtos.requests.UserInsertDTO;
import com.eduardo.user_cep_manager.repositories.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

		List<FieldMessageDTO> list = new ArrayList<>();

		if (repository.existsByCpf(dto.getCpf())) {
			list.add(new FieldMessageDTO("cpf", "Cpf já existe"));
		}

		for (FieldMessageDTO e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}