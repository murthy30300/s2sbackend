package com.klu.ss.service.Implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.klu.ss.DTO.FoodOfferDTO;
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
//    @Autowired
//    private UserRepo urp;

    @Override
    public FoodOffer saveFoodOffer(FoodOfferDTO foodOfferDTO) {
        return foodOfferRepository.save(foodOfferDTO.getFoodOffer());
    }

    @Override
    public Optional<FoodOffer> getFoodOfferById(long id) {
        return foodOfferRepository.findById(id);
    }

    @Override
    public List<Requesting> getRequestsForOffer(long offerId) {
        return rrp.findByFoodOffer_Foid(offerId);
    }

    @Transactional
    @Override
    public boolean confirmRequest(Long offerId, Long requesterId) {
        Optional<FoodOffer> foodOfferOpt = foodOfferRepository.findByFoidAndUser_Uid(offerId, requesterId);
        if (foodOfferOpt.isPresent()) {
            FoodOffer foodOffer = foodOfferOpt.get();
            foodOffer.setStatus(DonationStatus.CONFIRMED);
            foodOfferRepository.save(foodOffer);

            Profile donorProfile = prp.findByUserId(foodOffer.getUser().getUid());
            donorProfile.setScore(donorProfile.getScore() + 10);
            donorProfile.setTotalDonations(donorProfile.getTotalDonations() + 1);

            Profile recipientProfile = prp.findByUserId(requesterId);
            recipientProfile.setScore(recipientProfile.getScore() + 3);
            recipientProfile.setTotalReceived(recipientProfile.getTotalReceived() + 1);

            prp.save(donorProfile);
            prp.save(recipientProfile);
            return true;
        }
        return false;
    }

    @Transactional
    @Override
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
    public List<FoodOffer> getOffersByUserId(long userId) {
        return foodOfferRepository.findByUser_Uid(userId);
    }

    @Override
    public List<FoodOffer> getAllDonations() {
        return foodOfferRepository.findAllByStatus(DonationStatus.PENDING);
    }

    @Override
    public List<FoodOffer> getDonationsByUser(long userId) {
        return foodOfferRepository.findByUser_Uid(userId);
    }
}
