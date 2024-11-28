package com.klu.ss.DTO;

import java.time.LocalDateTime;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Profile;

import lombok.Data;
@Data
public class FoodRequestDto {
    private FoodOffer foodOffer;
    private Profile requester;
    private String status;
    private Long foid;
    private Long orgId;
    private LocalDateTime requestDate;
    private Organization orgreq;

    public Organization getOrgreq() {
		return orgreq;
	}

	public void setOrgreq(Organization orgreq) {
		this.orgreq = orgreq;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getFoid() {
		return foid;
	}

	public void setFoid(Long foid) {
		this.foid = foid;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public LocalDateTime getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(LocalDateTime requestDate) {
		this.requestDate = requestDate;
	}

	// No-argument constructor (required by Jackson for deserialization)
    public FoodRequestDto() {}

}
