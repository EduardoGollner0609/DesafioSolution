package com.eduardo.user_cep_manager.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.eduardo.user_cep_manager.dtos.errors.FieldMessageDTO;
import com.eduardo.user_cep_manager.dtos.requests.UserUpdateDTO;
import com.eduardo.user_cep_manager.entitites.User;
import com.eduardo.user_cep_manager.repositories.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		long userId = Long.parseLong(uriVars.get("userId"));

		List<FieldMessageDTO> list = new ArrayList<>();

		User user = repository.findByCpf(dto.getCpf());

		if (user != null && userId != user.getId()) {
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