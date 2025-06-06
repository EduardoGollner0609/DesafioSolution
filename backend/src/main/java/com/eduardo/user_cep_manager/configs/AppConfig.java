package com.eduardo.user_cep_manager.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

	@Value("${cors.origins}")
	private String corsOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String[] origins = corsOrigins.split(",");

		registry.addMapping("/**").allowedOrigins(origins).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*").allowCredentials(true);

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
