package com.klu.ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import com.klu.ss.model.enums.*;
@Entity
public class FoodDonation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fdid;
	//@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "foid", nullable = false)
//	private FoodOffer offer;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "donor_id", nullable = false)
	private User donor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipient_id", nullable = false)
	private User recipient;
	@Enumerated(EnumType.STRING)
	private DonationStatus status;
	private LocalDateTime pickuptime;
	private LocalDateTime completedAt;
	private String notes;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	public Long getFdid() {
		return fdid;
	}
	public void setFdid(Long fdid) {
		this.fdid = fdid;
	}
//	public FoodOffer getOffer() {
//		return offer;
//	}
//	public void setOffer(FoodOffer offer) {
//		this.offer = offer;
//	}
	public User getDonor() {
		return donor;
	}
	public void setDonor(User donor) {
		this.donor = donor;
	}
	public User getRecipient() {
		return recipient;
	}
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	public DonationStatus getStatus() {
		return status;
	}
	public void setStatus(DonationStatus status) {
		this.status = status;
	}
	public LocalDateTime getPickuptime() {
		return pickuptime;
	}
	public void setPickuptime(LocalDateTime pickuptime) {
		this.pickuptime = pickuptime;
	}
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}
	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
