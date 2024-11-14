package com.klu.ss.model;

import java.time.LocalDateTime;

import com.klu.ss.model.enums.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class FoodRequest {
	@Id
	private int frid;
	private String description;
    private String location;
    private Integer quantity;
    private LocalDateTime neededBy;
    @Enumerated(EnumType.STRING)
    private FoodType foodType; 
    private String dietaryRequirements;
    private String deliveryInstructions;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;
	public int getFrid() {
		return frid;
	}
	public void setFrid(int frid) {
		this.frid = frid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getNeededBy() {
		return neededBy;
	}
	public void setNeededBy(LocalDateTime neededBy) {
		this.neededBy = neededBy;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	public String getDietaryRequirements() {
		return dietaryRequirements;
	}
	public void setDietaryRequirements(String dietaryRequirements) {
		this.dietaryRequirements = dietaryRequirements;
	}
	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
}
