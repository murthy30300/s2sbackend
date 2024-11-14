package com.klu.ss.service.Implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.klu.ss.model.*;
import com.klu.ss.model.enums.*;
import com.klu.ss.repository.*;
import com.klu.ss.service.FoodOfferService;

import jakarta.transaction.Transactional;

@Service
public class OfferServeImpl implements FoodOfferService {
	@Autowired
	private FoodOfferRepository foodOfferRepository;
	@Autowired
	private RequestRepo rrp;
	@Autowired
	private ProfileRepo prp;
	@Override
	public FoodOffer saveFoodOffer(FoodOffer foodOffer) {
		return foodOfferRepository.save(foodOffer);
	}

	@Override
	public Optional<FoodOffer> getFoodOfferById(int id) {
		return foodOfferRepository.findById(id);
	}
//
//	@Override
//	public Optional<FoodOffer> getFoodOfferByUserId(int uid) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<FoodOffer> getAllOffers() {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<FoodOffer> myRequests(int id) {
//		// TODO Auto-generated method stub
//		return Optional.empty();
//	}
//
//	@Override
//	public Optional<FoodOffer> requests() {
//		  return foodOfferRepository.findByUser_Uid(uid);
//	}

	@Override
	public List<Requesting> getRequestsForOffer(int offerId) {
		 return rrp.findByFoodOffer_Foid(offerId);
	}

	@Transactional
    public boolean confirmRequest(Long offerId, Long requesterId) {
        Optional<FoodOffer> foodOfferOpt = foodOfferRepository.findByFoidAndUser_Uid(offerId, requesterId);

       // Optional<FoodOffer> foodOfferOpt = foodOfferRepository.findByIdAndUser_Uid(offerId, requesterId);
        if (foodOfferOpt.isPresent()) {
            FoodOffer foodOffer = foodOfferOpt.get();

            // Update donation status
            foodOffer.setStatus(DonationStatus.CONFIRMED);
            foodOfferRepository.save(foodOffer);

           
            Profile donorProfile = prp.findByUserId(foodOffer.getUser().getUid());
            donorProfile.setScore(donorProfile.getScore() + 10);
            donorProfile.setTotalDonations(donorProfile.getTotalDonations() + 1);

            // Update recipient profile (increases by 3 * total received)
            Profile recipientProfile = prp.findByUserId(requesterId);
            recipientProfile.setScore(recipientProfile.getScore() + 3);
            recipientProfile.setTotalReceived(recipientProfile.getTotalReceived() + 1);

            prp.save(donorProfile);
            prp.save(recipientProfile);

            return true;
        }
        return false;
    }
	@Override
	@Transactional
	public boolean markAsCompleted(Long offerId, Long requesterId) {
		  Optional<FoodOffer> foodOfferOpt = foodOfferRepository.findByFoidAndUser_Uid(offerId, requesterId);
	        if (foodOfferOpt.isPresent()) {
	            FoodOffer foodOffer = foodOfferOpt.get();
	            foodOffer.setStatus(DonationStatus.COMPLETED);
	            foodOfferRepository.save(foodOffer);
	            return true;
	        }
	        return false;
	}
	@Override
	public List<FoodOffer> getOffersByUserId(int userId) {
	    return foodOfferRepository.findByUser_Uid(userId);
	}
}
