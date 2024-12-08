package com.klu.ss.controller;

import java.util.Optional;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.klu.ss.DTO.FoodOfferDTO;
import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.User;
import com.klu.ss.service.FoodOfferService;
import com.klu.ss.service.UserService;
@RestController
@RequestMapping("/foodOffers")
@CrossOrigin(origins = "https://slacksurplus.netlify.app")
public class FoodOfferController {

    private final FoodOfferService foodOfferService;
    private final UserService usr;

    public FoodOfferController(UserService usr, FoodOfferService foodOfferService) {
        this.usr = usr;
        this.foodOfferService = foodOfferService;
    }

    @PostMapping
    public ResponseEntity<?> createFoodOffer(@RequestBody FoodOfferDTO foodOfferDTO) {
        String username = foodOfferDTO.getUsername();
        User user = usr.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("User not found with username: " + username);
        }

        foodOfferDTO.getFoodOffer().setUser(user);
        FoodOffer savedOffer = foodOfferService.saveFoodOffer(foodOfferDTO);
        return ResponseEntity.ok(savedOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOffer> getFoodOfferById(@PathVariable long id) {
        Optional<FoodOffer> foodOffer = foodOfferService.getFoodOfferById(id);
        return foodOffer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/mydonations")
    public ResponseEntity<List<FoodOffer>> getMyOffers(@RequestParam long userId) {
        List<FoodOffer> myOffers = foodOfferService.getOffersByUserId(userId);
        return ResponseEntity.ok(myOffers);
    }

    @GetMapping
    public List<FoodOffer> getAllDonations() {
        return foodOfferService.getAllDonations();
    }

    @GetMapping("/userdonations")
    public List<FoodOffer> getDonationsByUser(@RequestParam long userId) {
        return foodOfferService.getDonationsByUser(userId);
    }
}
