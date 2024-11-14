package com.klu.ss.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.Profile;
import com.klu.ss.service.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/posts")
public class ProfileController {
	@Autowired
	private ProfileService ps;
	@PostMapping("/update")
    public ResponseEntity<?> updateUser(
            @RequestParam Integer uid,
            @RequestParam(required = false) MultipartFile profilePic,
            @RequestParam(required = false) MultipartFile bannerPic,
            @RequestParam String name,
            @RequestParam LocalDate dateOfBirth,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam String badge) {
        
        try {
        	
            String result = ps.updateUserDetails(uid, profilePic, bannerPic, name, dateOfBirth, phone, address, badge);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
        	e.printStackTrace(); 
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the profile");
        }
    }
	@GetMapping("/profile/details")
    public ResponseEntity<Profile> getProfileDetails(@RequestParam String username) {
        Profile profileDetails = ps.getProfileDetailsByUsername(username);
        if (profileDetails != null) {
            return ResponseEntity.ok(profileDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
