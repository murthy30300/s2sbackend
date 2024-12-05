package com.klu.ss.service.Implementations;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.klu.ss.configs.OpenAi;
import com.klu.ss.service.OpenAiService;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAiImpl implements OpenAiService {
	private final RestTemplate restTemplate;
	private final OpenAi openAIConfig;

	public OpenAiImpl(RestTemplate restTemplate, OpenAi openAIConfig) {
		this.restTemplate = restTemplate;
		this.openAIConfig = openAIConfig;
	}

	@Override
	public String generateText(String prompt) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Authorization", "Bearer " + openAIConfig.getApiKey());

	    // Payload
	    Map<String, Object> payload = new HashMap<>();
	    payload.put("model", "gpt-3.5-turbo");
	    payload.put("messages", new Object[]{Map.of("role", "user", "content", prompt)});
	    payload.put("max_tokens", 100);
	    payload.put("temperature", 0.7);

	    // HTTP Entity
	    HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

	    try {
	        // POST Request
	        ResponseEntity<String> response = restTemplate.postForEntity(openAIConfig.getApiUrl(), request, String.class);

	        if (response.getStatusCode() == HttpStatus.OK) {
	            return response.getBody();
	        } else if (response.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
	            throw new RuntimeException("API quota exceeded. Please try again later or check your OpenAI plan.");
	        } else {
	            throw new RuntimeException("Failed to fetch response from OpenAI API: " + response.getStatusCode());
	        }
	    } catch (HttpClientErrorException e) {
	        if (e.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS) {
	            throw new RuntimeException("API quota exceeded. Please try again later.");
	        } else {
	            throw new RuntimeException("An error occurred: " + e.getMessage());
	        }
	    } catch (Exception e) {
	        throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
	    }
	}


}
