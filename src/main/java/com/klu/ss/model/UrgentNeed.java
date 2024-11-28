package com.klu.ss.model;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "recipient_stats")
public class UrgentNeed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long urid;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User organization;

	private String title;
	private String description;
	private Integer quantityNeeded;
	private LocalDateTime neededBy;
	private String eventType;
	private Boolean isActive = true;
	private LocalDateTime createdAt = LocalDateTime.now();
	public Long getId() {
		return urid;
	}
	public void setId(Long urid) {
		this.urid = urid;
	}
	public User getOrganization() {
		return organization;
	}
	public void setOrganization(User organization) {
		this.organization = organization;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantityNeeded() {
		return quantityNeeded;
	}
	public void setQuantityNeeded(Integer quantityNeeded) {
		this.quantityNeeded = quantityNeeded;
	}
	public LocalDateTime getNeededBy() {
		return neededBy;
	}
	public void setNeededBy(LocalDateTime neededBy) {
		this.neededBy = neededBy;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
