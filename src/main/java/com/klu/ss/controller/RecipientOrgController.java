package com.klu.ss.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Post;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.enums.FoodType;
import com.klu.ss.repository.OrganizationRepo;
import com.klu.ss.service.RecipientOrgService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/recipient")
public class RecipientOrgController {

	@Autowired
	private RecipientOrgService recipientOrgService;
	@Autowired
	private OrganizationRepo orp;
	@GetMapping("/donations")
	public ResponseEntity<List<FoodOffer>> getFilteredDonations(@RequestParam String location,
			@RequestParam(required = false) FoodType foodType,
			@RequestParam(required = false) LocalDateTime expiryDate) {

		List<FoodOffer> foodOffers = recipientOrgService.getFilteredDonations(location, foodType, expiryDate);
		return ResponseEntity.ok(foodOffers);
	}

	// Find nearby logistics providers for a given food offer
	@GetMapping("/logistics")
	public ResponseEntity<List<LogisticsProvider>> findNearbyLogistics(@RequestParam long donationId,
			@RequestParam long recipientId) {

		List<LogisticsProvider> logisticsProviders = recipientOrgService.findNearbyLogistics(donationId, recipientId);
		return ResponseEntity.ok(logisticsProviders);
	}

	// Get recipient organization statistics (e.g., food received, people served)
	@GetMapping("/stats")
	public ResponseEntity<RecipientStatus> getStats(@RequestParam long organizationId) {
		RecipientStatus stats = recipientOrgService.getStats(organizationId);
		return ResponseEntity.ok(stats);
	}

	// Get the request history for a recipient organization
	@GetMapping("/request-history")
	public ResponseEntity<List<Requesting>> getRequestHistory(@RequestParam long organizationId,
			@RequestParam(required = false) Requesting.RequestStatus status) {

		List<Requesting> requestHistory = recipientOrgService.getRequestHistory(organizationId, status);
		System.out.println("Organization ID: " + organizationId);
		System.out.println("Request status: " + status);
		for (Requesting request : requestHistory) {
		    System.out.println("Request ID: " + request.getRid());
		    System.out.println("Request Date:===== " + request.getRequestDate());
		    System.out.println("Status: " + request.getStatus());

		    if (request.getFoodOffer() != null) {
		        System.out.println("Food Offer ID: " + request.getFoodOffer().getFoid());
		        System.out.println("Food Offer Name: " + request.getFoodOffer().getDescription());
		    } else {
		        System.out.println("Food Offer: null");
		    }

		    if (request.getOrg() != null) {
		        System.out.println("Organization ID: " + request.getOrg().getId());
		        System.out.println("Organization Name: " + request.getOrg().getName());
		    } else {
		        System.out.println("Organization: null");
		    }

		    if (request.getId() != 0) {
		        System.out.println("Requester ID: " + request.getId());
		    } else {
		        System.out.println("Requester ID: null");
		    }

		    System.out.println("-------------------------");
		}
		//System.out.println("Fetched history: " + requestHistory.get(status));
		return ResponseEntity.ok(requestHistory);
	}
	@GetMapping("/getorganizer")
	public ResponseEntity<?> getOrganizer(@RequestParam long uid){
		Organization org = orp.findByUserUid(uid);
		System.out.println(org.getId());
		return ResponseEntity.ok(org.getId());
	}
	// Create an urgent need post for specific food resources
	@PostMapping("/urgent-need")
	public ResponseEntity<UrgentNeed> createUrgentNeed(@RequestBody UrgentNeed urgentNeed) {
		UrgentNeed createdUrgentNeed = recipientOrgService.createUrgentNeed(urgentNeed);
		return ResponseEntity.ok(createdUrgentNeed);
	}

	// Calculate distribution plan for a received food request
	@GetMapping("/distribution-plan")
	public ResponseEntity<Map<String, Object>> calculateDistributionPlan(@RequestParam long requestId) {
		Map<String, Object> distributionPlan = recipientOrgService.calculateDistributionPlan(requestId);
		return ResponseEntity.ok(distributionPlan);
	}

	// Add a success story with a potential image upload
	@PostMapping("/success-story")
	public ResponseEntity<Post> addSuccessStory(@RequestParam long organizationId, @RequestParam String story,
			@RequestParam(required = false) MultipartFile image) {

		Post successStory = recipientOrgService.addSuccessStory(organizationId, story, image);
		return ResponseEntity.ok(successStory);
	}

	// Create a food request (for receiving donations)
	@PostMapping("/food-request")
    public ResponseEntity<?> createFoodRequest(@RequestBody Map<String, Object> requestData) {
        try {
            // Extract data from request
            Map<String, Object> foodOfferMap = (Map<String, Object>) requestData.get("foodOffer");
            Map<String, Object> requesterMap = (Map<String, Object>) requestData.get("requester");
            
            long foodOfferId = ((Number) foodOfferMap.get("foid")).intValue();
            long userId = ((Number) requesterMap.get("uid")).intValue();
            
            Requesting createdRequest = recipientOrgService.createFoodRequest(foodOfferId, userId);
            return ResponseEntity.ok(createdRequest);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating request: " + e.getMessage());
        }
    
	}

	// Update the status of a food request (e.g., accepted, completed, etc.)
	@PutMapping("/update-request-status")
	public ResponseEntity<Requesting> updateRequestStatus(@RequestParam long requestId,
			@RequestParam Requesting.RequestStatus status) {

		Requesting updatedRequest = recipientOrgService.updateRequestStatus(requestId, status);
		return ResponseEntity.ok(updatedRequest);
	}
}
