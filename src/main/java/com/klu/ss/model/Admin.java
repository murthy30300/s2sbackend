package com.klu.ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
@Entity
public class Admin {
	@Id
	private long aid;
	public long getId() {
		return aid;
	}
	public void setId(long aid) {
		this.aid = aid;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@JoinColumn(name="uid",nullable=false)
	@OneToOne
	private User user;
	

}
