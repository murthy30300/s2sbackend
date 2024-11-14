package com.klu.ss.model;
//import com.klu.ss.model.enums.RequestStatus;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "requesting")
public class Requesting {
    //public static final String RequestStatus = "PENDING";
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_offer_id", nullable = false)
    private FoodOffer foodOffer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private Profile requester;
    @Enumerated(EnumType.STRING)
    public RequestStatus status;
    private LocalDateTime requestDate = LocalDateTime.now();
    // Constructors, getters, and setters
    public Requesting() {
       
    }
    public enum RequestStatus {
        PENDING,
        CONFIRMED,
        COMPLETED,
        CANCELLED
    }
   // public RequestStatus = status;
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodOffer getFoodOffer() {
		return foodOffer;
	}

	public void setFoodOffer(FoodOffer foodOffer) {
		this.foodOffer = foodOffer;
	}

	public Profile getRequester() {
		return requester;
	}

	public void setRequester(Profile requester) {
		this.requester = requester;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	
}
