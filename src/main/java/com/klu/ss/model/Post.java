package com.klu.ss.model;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
@Entity
public class Post {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    @JsonIgnore
    private User user; 
    private String caption;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; 
    private boolean isActive = true;
    private int likeCount=0;
 // New transient fields to hold the username and profilePicUrl
    @Transient
    private String username;

    @Transient
    private String profilePicUrl;

    public String getUsername() {
        if (user != null) {
            return user.getUsername();
        }
        return null;
    }

    public String getProfilePicUrl() {
        if (user != null && user.getProfile() != null) {
            return user.getProfile().getProfilePicUrl();
        }
        return null;
    }
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public Long getId() {
		return pid;
	}
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	public void setId(Long pid) {
		this.pid = pid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setPostType(String string) {
		// TODO Auto-generated method stub
		
	}
	public void setLikes(int likeCount) {
		// TODO Auto-generated method stub
		
	}
	public void setLiked(boolean likedByProfile) {
		// TODO Auto-generated method stub
		
	}
}
