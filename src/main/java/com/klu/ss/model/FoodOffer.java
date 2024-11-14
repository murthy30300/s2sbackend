package com.klu.ss.model;

import java.time.LocalDateTime;
		
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.klu.ss.model.enums.*;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
//@DiscriminatorValue("OFFER")
public class FoodOffer /*extends Post*/{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foid;
	private String description;
    private String location;
    private Integer quantity;
    private LocalDateTime expiryDate;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    @Enumerated(EnumType.STRING)
    private PackagingType packagingType;
    private String pickupInstructions;
    private boolean isPerishable;
    private String dietaryNotes;
    @Enumerated(EnumType.STRING)
	private DonationStatus status;
    @ManyToOne
    @JoinColumn(name = "uid")
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "foodOffer")
    private List<Requesting> requests;
	public int getFoid() {
		return foid;
	}
	public void setFoid(int foid) {
		this.foid = foid;
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
	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	public PackagingType getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(PackagingType packagingType) {
		this.packagingType = packagingType;
	}
	public String getPickupInstructions() {
		return pickupInstructions;
	}
	public void setPickupInstructions(String pickupInstructions) {
		this.pickupInstructions = pickupInstructions;
	}
	public boolean isPerishable() {
		return isPerishable;
	}
	public void setPerishable(boolean isPerishable) {
		this.isPerishable = isPerishable;
	}
	public String getDietaryNotes() {
		return dietaryNotes;
	}
	public void setDietaryNotes(String dietaryNotes) {
		this.dietaryNotes = dietaryNotes;
	}
	public void setStatus(DonationStatus completed) {
		status = completed;
		
	}
	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}
    
	

}