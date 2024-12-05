package com.klu.ss.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.klu.ss.service.OpenAiService;

@RestController
@RequestMapping("/openai/recipes")
@CrossOrigin(origins = "http://localhost:5173")
public class OpenAiController {
	private final OpenAiService os;

	public OpenAiController(OpenAiService os) {
		this.os = os;
	}

	@PostMapping("/generate")
	public ResponseEntity<String> generateRecipe(@RequestBody List<String> items) {
		if (items == null || items.isEmpty()) {
			return ResponseEntity.badRequest().body("The list of items cannot be empty.");
		}

		// Create a prompt using the list of food items
		String prompt = "Generate a recipe using the following ingredients: " + String.join(", ", items);

		try {
			// Call the OpenAiService to generate text
			String recipe = os.generateText(prompt);
			return ResponseEntity.ok(recipe);
		} catch (RuntimeException e) {
			if (e.getMessage().contains("quota exceeded")) {
				return ResponseEntity.status(429).body("Error: " + e.getMessage());
			} else {
				return ResponseEntity.status(500).body("Error: Failed to generate a recipe. " + e.getMessage());
			}
		}
	}

}
