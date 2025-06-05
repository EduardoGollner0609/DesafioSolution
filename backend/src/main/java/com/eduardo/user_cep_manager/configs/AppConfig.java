package com.eduardo.user_cep_manager.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Value("${cors.origins}")
	private String corsOrigins;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
