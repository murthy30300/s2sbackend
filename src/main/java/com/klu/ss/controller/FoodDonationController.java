package com.klu.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klu.ss.model.FoodDonation;
import com.klu.ss.service.FoodDonationService;
@RequestMapping("/foodDonations/")
@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class FoodDonationController {
	@Autowired
	private  FoodDonationService foodDonationService;
    @PostMapping("/")
    public ResponseEntity<FoodDonation> createFoodDonation(@RequestBody FoodDonation foodDonation) {
        FoodDonation savedDonation = foodDonationService.saveFoodDonation(foodDonation);
        return ResponseEntity.ok(savedDonation);
    }
}
