package com.klu.ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class NGO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ngoId;
	private String name;
	private String head; // Representative or contact person
	private int totalOfferings;
	private int totalReceivings;
	private int points;
	private String badge;
	@OneToOne
	private User user;
	public int getNgoId() {
		return ngoId;
	}
	public void setNgoId(int ngoId) {
		this.ngoId = ngoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getTotalOfferings() {
		return totalOfferings;
	}
	public void setTotalOfferings(int totalOfferings) {
		this.totalOfferings = totalOfferings;
	}
	public int getTotalReceivings() {
		return totalReceivings;
	}
	public void setTotalReceivings(int totalReceivings) {
		this.totalReceivings = totalReceivings;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getBadge() {
		return badge;
	}
	public void setBadge(String badge) {
		this.badge = badge;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	} 

}
