package com.klu.ss.service.Implementations;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Post;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.User;
import com.klu.ss.model.enums.FoodType;

import com.klu.ss.repository.FoodOfferRepository;
import com.klu.ss.repository.LogisticsProviderRepository;
import com.klu.ss.repository.OrganizationRepo;
import com.klu.ss.repository.PostRepo;
import com.klu.ss.repository.RecipentStatusRepository;
import com.klu.ss.repository.RequestRepo;
import com.klu.ss.repository.UrgentNeedRepository;
import com.klu.ss.repository.UserRepo;

import com.klu.ss.service.RecipientOrgService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class RecipientImpl implements RecipientOrgService {
	@Autowired
	private FoodOfferRepository foodOfferRepo;
	@Autowired
	private LogisticsProviderRepository logisticsRepo;
	@Autowired
	private RecipentStatusRepository statsRepo;
	@Autowired
	private UrgentNeedRepository urgentNeedRepo;
	@Autowired
	private RequestRepo requestRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo urp;
	@Autowired
	private OrganizationRepo orp;

	@Override
	public List<FoodOffer> getFilteredDonations(String location, FoodType foodType, LocalDateTime expiryDate) {
		return foodOfferRepo.findByFilters(location, foodType, expiryDate);

	}

	@Override
	public List<LogisticsProvider> findNearbyLogistics(long donationId, long recipientId) {
		FoodOffer offer = foodOfferRepo.findById(donationId).orElseThrow();
		return logisticsRepo.findNearbyProviders(offer.getLatitude(), offer.getLongitude(), 10.0);
	}

	@Override
	public RecipientStatus getStats(long organizationId) {
		return statsRepo.findByOrganizationId(organizationId).orElse(new RecipientStatus());
	}

	@Override
	public List<Requesting> getRequestHistory(long organizationId, Requesting.RequestStatus status) {
		if (status != null) {
			// System.err.println("Fetching requests for organizationId={} with status={}",
			// organizationId, status);
			return requestRepo.findByRequesterIdAndStatus(organizationId, status);
		}
		// System.out.println("Fetching all requests for organizationId={}",
		// organizationId);
		return requestRepo.findByRequesterId(organizationId);
	}

	@Override
	public UrgentNeed createUrgentNeed(UrgentNeed urgentNeed) {
		return urgentNeedRepo.save(urgentNeed);
	}

	@Override
	public Map<String, Object> calculateDistributionPlan(long requestId) {
		Requesting request = requestRepo.findById(requestId).orElseThrow();
		int totalQuantity = request.getFoodOffer().getQuantity();
		int estimatedServings = totalQuantity * 2; // Simple calculation

		Map<String, Object> plan = new HashMap<>();
		plan.put("totalQuantity", totalQuantity);
		plan.put("estimatedServings", estimatedServings);
		plan.put("suggestedPortionSize", totalQuantity / estimatedServings);

		return plan;
	}

	public Post addSuccessStory(long userId, String story, MultipartFile image) {
	    Post post = new Post();
	    post.setCaption(story);

	    if (image != null) {
	        String imageUrl = uploadImage(image); 
	        post.setImageUrl(imageUrl);
	    }

	    System.out.println("Fetching organization for user ID: " + userId);

	    // Fetch the Organization by user ID
	    Organization organization = orp.findByUserUid(userId);
	      //  .orElseThrow(() -> new RuntimeException("Organization not found for user ID: " + userId));

	    System.out.println("Organization fetched: " + organization.getId());

	    User user = organization.getUser();
	    if (user == null) {
	        throw new RuntimeException("User not associated with organization: " + organization.getId());
	    }

	    System.out.println("UserId fetched for organization: " + user.getUid());

	    // Set the user to the Post
	    post.setUser(user);

	    return postRepo.save(post);
	}



	private static final long MAX_SIZE = 5 * 1024 * 1024; // Maximum size of the image (5MB)
	@Autowired
	private Cloudinary cloudinary;

	public String uploadImage(MultipartFile image) {
		if (image.getSize() > MAX_SIZE) {
			throw new IllegalArgumentException("Profile picture is too large");
		}

		Map<?, ?> uploadResult = null;
		try {
			uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error uploading image");
		}
		return (String) uploadResult.get("url");
	}

	@Override
	@Transactional
	public Requesting createFoodRequest(long foodOfferId, long userId) {
	    try {
	        System.out.println("*****************");
	        System.out.println("Food Offer ID: " + foodOfferId);
	        System.out.println("User ID: " + userId);

	        FoodOffer foodOffer = foodOfferRepo.findById(foodOfferId)
	                .orElseThrow(() -> new RuntimeException("Food offer not found"));

	        System.out.println("Food Offer Found: " + foodOffer);

	        User user = urp.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	        System.out.println("User Found: " + user);

	        Organization organization = orp.findByUser(user)
	                .orElseThrow(() -> new RuntimeException("Organization not found"));

	        System.out.println("Organization Found: " + organization);

	        Requesting request = new Requesting();
	        request.setFoodOffer(foodOffer);
	        request.setStatus(Requesting.RequestStatus.PENDING);
	        request.setRequestDate(LocalDateTime.now());
	        request.setOrg(organization);

	        // Log request before saving
	        System.out.println("Request Before Saving: " + request);

	        Requesting savedRequest = requestRepo.save(request);
	        System.out.println("Saved Request: " + savedRequest);

	        return savedRequest;

	    } catch (Exception e) {
	        System.out.println("Error during food request creation: " + e.getMessage());
	        throw new RuntimeException("Error creating food request: " + e.getMessage());
	    }
	}


	public Requesting updateRequestStatus(long requestId, Requesting.RequestStatus status) {
		Requesting request = requestRepo.findById(requestId)
				.orElseThrow(() -> new EntityNotFoundException("Request not found"));
		request.setStatus(status);
		return requestRepo.save(request);
	}

	public String updateOrganizationDetails(long uid, String name, String description, String contactEmail,
			String contactPhone, String address) {

	// Find the user by UID (userId from session storage)
	User user = urp.findById(uid)
			.orElseThrow(() -> new IllegalArgumentException("User not found"));

	// Create or update organization for this user
	Organization organization = orp.findByUserp(user);
	if (organization == null) {
		organization = new Organization();
		organization.setUser(user);
	}

	// Set organization details
	organization.setName(name);
	organization.setDescription(description);
	organization.setContactEmail(contactEmail);
	organization.setContactPhone(contactPhone);
	organization.setAddress(address);

	orp.save(organization);

	return "Organization details updated successfully";
}
}
