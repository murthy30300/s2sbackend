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

	@Override
	public List<Requesting> getRequestsForOffer(long offerId) {
		System.out.println("Fetching requests for offer: " + offerId);
		List<Requesting> results = requestRepository.findByFoodOffer_Foid(offerId);
		System.out.println("Found " + results.size() + " results");
		return results;
	}

	@Override
	public List<Requesting> getRequestsByUser(Long requesterId) {
		return requestRepository.findByRequester_Prid(requesterId);
	}

	@Override
	@Transactional
	public Requesting createRequest(long foodOfferId, long requesterId) {
		FoodOffer foodOffer = foodOfferRepository.findById(foodOfferId)
				.orElseThrow(() -> new RuntimeException("Food offer not found"));
		Profile requester = profileRepository.getByUid(requesterId)
				.orElseThrow(() -> new RuntimeException("Requester profile not found"));

		Requesting request = new Requesting();
		request.setFoodOffer(foodOffer);
		request.setRequester(requester);
		request.setStatus(Requesting.RequestStatus.PENDING);

		return requestRepository.save(request);
	}

	@Override
	@Transactional
	public boolean confirmRequest(Long requestId) {
		Requesting request = requestRepository.findById(requestId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		request.setStatus(Requesting.RequestStatus.CONFIRMED);
		requestRepository.save(request);
		return true;
	}

	@Override
	@Transactional
	public boolean markAsCompleted(Long requestId) {
		Requesting request = requestRepository.findById(requestId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		request.setStatus(Requesting.RequestStatus.COMPLETED);
		requestRepository.save(request);
		return true;
	}

	@Override
	@Transactional
	public boolean cancelRequest(Long requestId) {
		Requesting request = requestRepository.findById(requestId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		request.setStatus(Requesting.RequestStatus.CANCELLED);
		requestRepository.save(request);
		return true;
	}

	@Override
	@Transactional
	public boolean updateRequestStatus(Long requestId, Requesting.RequestStatus status) {
		Requesting request = requestRepository.findById(requestId)
				.orElseThrow(() -> new RuntimeException("Request not found"));
		request.setStatus(status);
		requestRepository.save(request);
		return true;
	}
}