package com.klu.ss.configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class OpenAi {
	@Value("${openai.api.key}")
	private String apiKey;

	@Value("${openai.api.url}")
	private String apiUrl;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getApiUrl() {
		return apiUrl;
	}

}
