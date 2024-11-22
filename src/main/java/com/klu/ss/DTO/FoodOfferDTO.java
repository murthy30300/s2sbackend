package com.klu.ss.DTO;

import com.klu.ss.model.FoodOffer;
public class FoodOfferDTO {
    private FoodOffer foodOffer;
    private String username;

    // Getters and Setters
    public FoodOffer getFoodOffer() {
        return foodOffer;
    }

    public void setFoodOffer(FoodOffer foodOffer) {
        this.foodOffer = foodOffer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
