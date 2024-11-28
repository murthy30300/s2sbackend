package com.klu.ss.model;

//import com.klu.ss.model.enums.RequestStatus;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	// public static final String RequestStatus = "PENDING";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long rid;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime requestDate = LocalDateTime.now();
	@Enumerated(EnumType.STRING)
	public RequestStatus status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	@JsonIgnore
	private Organization org;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "food_offer_id", nullable = false)
	private FoodOffer foodOffer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "requester_id")
	@JsonIgnore
	private Profile requester;

	public enum RequestStatus {
		PENDING, CONFIRMED, COMPLETED, CANCELLED
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	// Constructors, getters, and setters
	public Requesting() {

	}

	// public RequestStatus = status;
	public long getId() {
		return rid;
	}

	public void setId(long rid) {
		this.rid = rid;
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