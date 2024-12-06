package com.klu.ss.model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
@Entity
public class Profile {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prid;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "uid", unique = true, nullable = false)
	@JsonIgnore
	private User user; 
    private String name;
    String username;
    private LocalDate dateOfBirth;  
    private String phone;
    private String address;
    private Integer score = 0;
    private String profilePicUrl;
    private String bannerPicUrl;
    private Integer totalDonations = 0;
    private Integer totalReceived = 0;
    private String badge;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    public String getUsername() {
		return username;
	}
    public long getUid(){
    	return user.uid;
    	
    }
    public void setUid(long uid) {
    	user.uid =uid;
    }
	public void setUsername(String username) {
		this.username = username;
	}
	public Profile(String username, String profilePicUrl2, String bannerPicUrl2) {
		this.username= username;
		profilePicUrl2 = profilePicUrl;
		bannerPicUrl2 = bannerPicUrl;
		
	}
    public Profile() {}
	public Long getPrid() {
		return prid;
	}
	public void setPrid(Long prid) {
		this.prid = prid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public String getBannerPicUrl() {
		return bannerPicUrl;
	}
	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}
	public Integer getTotalDonations() {
		return totalDonations;
	}
	public void setTotalDonations(Integer totalDonations) {
		this.totalDonations = totalDonations;
	}
	public Integer getTotalReceived() {
		return totalReceived;
	}
	public void setTotalReceived(Integer totalReceived) {
		this.totalReceived = totalReceived;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
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
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
}
