package com.klu.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.klu.ss.service.*;
import com.klu.ss.model.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = "http://localhost:5173")
public class RequestController {
	@Autowired
	private RequestService requestService;

	@GetMapping("/user/{requesterId}")
	public List<Requesting> getRequestsByUser(@PathVariable Long requesterId) {
		return requestService.getRequestsByUser(requesterId);
	}

	@GetMapping("/offer/{offerId}")
	public List<Requesting> getRequestsForOffer(@PathVariable long offerId) {
		System.out.println("---------");
		return requestService.getRequestsForOffer(offerId)
			    .stream()
			    .peek(request -> System.out.println("Request Status: " + request.getStatus()))
			    .filter(request -> "PENDING".equals(request.getStatus()))
			    .collect(Collectors.toList());
	}

	@PostMapping("/create")
	public ResponseEntity<?> createRequest(@RequestParam(required = true) long offerId,
			@RequestParam(required = true) long requesterId) {
		try {
			Requesting request = requestService.createRequest(offerId, requesterId);
			return ResponseEntity.ok(request);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

	@PostMapping("/{requestId}/confirm")
	public boolean confirmRequest(@PathVariable Long requestId) {
		boolean isConfirmed = requestService.confirmRequest(requestId);
		if (!isConfirmed) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Request confirmation failed.");
		}
		return isConfirmed;
	}

	@PostMapping("/{requestId}/complete")
	public boolean markAsCompleted(@PathVariable Long requestId) {
		return requestService.markAsCompleted(requestId);
	}

	@PostMapping("/{requestId}/cancel")
	public boolean cancelRequest(@PathVariable Long requestId) {
		return requestService.cancelRequest(requestId);
	}

	@PostMapping("/donation/status")
	public boolean updateDonationStatus(@RequestParam Long requestId, @RequestParam Requesting.RequestStatus status) {
		return requestService.updateRequestStatus(requestId, status);
	}
}