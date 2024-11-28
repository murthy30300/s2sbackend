package com.klu.ss.model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
public class RecipientStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsid;
    
    @OneToOne
    @JoinColumn(name = "uid")
    private User organization;

    private Integer totalFoodReceived = 0;
    private Integer peopleFed = 0;
    private Integer successfulRequests = 0;
    private LocalDateTime lastUpdated = LocalDateTime.now();
	public Long getRsid() {
		return rsid;
	}
	public void setRsid(Long rsid) {
		this.rsid = rsid;
	}
	public User getOrganization() {
		return organization;
	}
	public void setOrganization(User organization) {
		this.organization = organization;
	}
	public Integer getTotalFoodReceived() {
		return totalFoodReceived;
	}
	public void setTotalFoodReceived(Integer totalFoodReceived) {
		this.totalFoodReceived = totalFoodReceived;
	}
	public Integer getPeopleFed() {
		return peopleFed;
	}
	public void setPeopleFed(Integer peopleFed) {
		this.peopleFed = peopleFed;
	}
	public Integer getSuccessfulRequests() {
		return successfulRequests;
	}
	public void setSuccessfulRequests(Integer successfulRequests) {
		this.successfulRequests = successfulRequests;
	}
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
    
    // Getters and setters
}