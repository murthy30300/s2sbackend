package com.klu.ss.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
@Entity
public class Requester {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int requestId;
	    private String requestDetails;

	    @OneToOne
	    @JoinColumn(name = "pid")
	    private Post post;

	    @ManyToOne
	    @JoinColumn(name = "prid")
	    private Profile recipientProfile;

		public int getRequestId() {
			return requestId;
		}

		public void setRequestId(int requestId) {
			this.requestId = requestId;
		}

		public String getRequestDetails() {
			return requestDetails;
		}

		public void setRequestDetails(String requestDetails) {
			this.requestDetails = requestDetails;
		}

		public Post getPost() {
			return post;
		}

		public void setPost(Post post) {
			this.post = post;
		}

		public Profile getRecipientProfile() {
			return recipientProfile;
		}

		public void setRecipientProfile(Profile recipientProfile) {
			this.recipientProfile = recipientProfile;
		}


}
