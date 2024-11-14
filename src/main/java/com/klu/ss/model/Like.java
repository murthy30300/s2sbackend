package com.klu.ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="post_like")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int likeId;

	@ManyToOne
	@JoinColumn(name = "prid")
	private Profile profile;

	@ManyToOne
	@JoinColumn(name = "pid")
	private Post post;

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
