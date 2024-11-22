package com.klu.ss.service;

import java.util.*;

import com.klu.ss.DTO.FoodOfferDTO;
import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.Requesting;
public interface FoodOfferService {
    FoodOffer saveFoodOffer(FoodOfferDTO foodOfferDTO);
    Optional<FoodOffer> getFoodOfferById(int oid);
    List<FoodOffer> getOffersByUserId(int userId);
    List<Requesting> getRequestsForOffer(int offerId);
    boolean confirmRequest(Long offerId, Long requesterId);
    boolean markAsCompleted(Long offerId, Long requesterId);
    List<FoodOffer> getAllDonations();
    List<FoodOffer> getDonationsByUser(int userId);
}

