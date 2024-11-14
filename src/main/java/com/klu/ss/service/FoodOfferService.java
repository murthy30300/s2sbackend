package com.klu.ss.service;

import java.util.*;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.Requesting;

public interface FoodOfferService {
	  FoodOffer saveFoodOffer(FoodOffer foodOffer);
	    Optional<FoodOffer> getFoodOfferById(int oid);
	    List<FoodOffer> getOffersByUserId(int userId);  // New method for user-specific offers
	    List<Requesting> getRequestsForOffer(int offerId);
	    boolean confirmRequest(Long offerId, Long requesterId);
	    boolean markAsCompleted(Long offerId, Long requesterId);
	 

}

//	 public Optional<FoodOffer> getFoodOfferByUserId(int uid);
//	 public Optional<FoodOffer> getAllOffers();
//	 public Optional<FoodOffer> myRequests(int id);
//	 public Optional<FoodOffer> requests();

