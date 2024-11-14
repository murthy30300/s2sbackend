package com.klu.ss.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.ss.service.*;

import jakarta.transaction.Transactional;
import com.klu.ss.model.*;
import com.klu.ss.repository.*;
import java.util.*;
@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepo requestRepository;

    @Autowired
    private ProfileRepo profileRepository;

    @Autowired
    private FoodOfferRepository foodOfferRepository;

    public List<Requesting> getRequestsForOffer(int offerId) {
        return requestRepository.findByFoodOffer_Foid(offerId);
    }
    
    public List<Requesting> getRequestsByUser(Long requesterId) {
        return requestRepository.findByRequester_Prid(requesterId);
    }

    @Transactional
    public Requesting createRequest(int foodOfferId, Long requesterId) {
        FoodOffer foodOffer = foodOfferRepository.findById(foodOfferId)
            .orElseThrow(() -> new RuntimeException("Food offer not found"));
        Profile requester = profileRepository.findById(requesterId)
            .orElseThrow(() -> new RuntimeException("Requester profile not found"));

        Requesting request = new Requesting();
        request.setFoodOffer(foodOffer);
        request.setRequester(requester);
        request.setStatus(Requesting.RequestStatus.PENDING);

        return requestRepository.save(request);
    }

    @Transactional
    public boolean confirmRequest(Long requestId) {
        Requesting request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(Requesting.RequestStatus.CONFIRMED);
        requestRepository.save(request);

        return true;
    }

    @Transactional
    public boolean markAsCompleted(Long requestId) {
        Requesting request = requestRepository.findById(requestId)
            .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(Requesting.RequestStatus.COMPLETED);
        requestRepository.save(request);

        return true;
    }

}
