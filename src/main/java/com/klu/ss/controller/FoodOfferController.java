package com.klu.ss.controller;

import java.util.Optional;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.service.FoodOfferService;

@RestController
@RequestMapping("/foodOffers")
@CrossOrigin(origins = "http://localhost:5173")
public class FoodOfferController {
	@Autowired
    private  FoodOfferService foodOfferService;
    @PostMapping
    public ResponseEntity<FoodOffer> createFoodOffer(@RequestBody FoodOffer foodOffer) {
        FoodOffer savedOffer = foodOfferService.saveFoodOffer(foodOffer);
        return ResponseEntity.ok(savedOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOffer> getFoodOfferById(@PathVariable int id) {
        Optional<FoodOffer> foodOffer = foodOfferService.getFoodOfferById(id);
        return foodOffer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/mydonations")
    public ResponseEntity<List<FoodOffer>> getMyOffers(@RequestParam int userId) {
        List<FoodOffer> myOffers = foodOfferService.getOffersByUserId(userId);
        return ResponseEntity.ok(myOffers);
    }
}
