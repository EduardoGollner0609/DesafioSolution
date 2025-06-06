package com.eduardo.user_cep_manager.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI userCepManager() {
		return new OpenAPI().info(new Info().title("User Cep Manager API").description("User Cep Manager Project")
				.version("v0.0.1")
				.license(new License().name("Apache 2.0").url("https://github.com/EduardoGollner0609/UserCepManager")));
	}

}
