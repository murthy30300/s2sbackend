package com.klu.ss.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klu.ss.model.User;
import com.klu.ss.service.UserService;


@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})@Controller
public class ViewController {
	@Autowired
	UserService usr;

	@PostMapping("/signup")
	@ResponseBody
	public ResponseEntity<String> signup(@RequestBody User user) {
		String message = usr.signup(user);
		return ResponseEntity.ok(message);
	}
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		User user = usr.login(username, password);
		if (user != null) {
			// Return a JSON object with user details
			Map<String, Object> response = new HashMap<>();
			response.put("user", user);
		
			return ResponseEntity.ok(response); // Responds with user details
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
	 @GetMapping("/getUserIdByUsername")
	    public ResponseEntity<?> getUserIdByUsername(@RequestParam("username") String username) {
	        try {
	            Long userId = usr.getUserIdByUsername(username);
	            System.out.println(userId);
	            if (userId == null) {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	            }
	            return ResponseEntity.ok(userId);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving userId");
	        }
	    }
	

}
