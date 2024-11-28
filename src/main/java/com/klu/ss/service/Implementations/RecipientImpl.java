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

	@Override
	public Post addSuccessStory(long organizationId, String story, MultipartFile image) {
		Post post = new Post();
		post.setCaption(story);
		if (image != null) {
			String imageUrl = uploadImage(image); // image upload
			post.setImageUrl(imageUrl);
		}
		System.err.println(organizationId);
		System.err.println(story);
		User user = urp.findById(organizationId).orElseThrow(() -> new RuntimeException("User not found"));
		System.out.println("User fetched for organizationId: " + user.getUid());

		post.setUser(urp.findById(organizationId).orElseThrow());
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
		// Find the food offer
		FoodOffer foodOffer = foodOfferRepo.findById(foodOfferId)
				.orElseThrow(() -> new RuntimeException("Food offer not found"));

		// Find the organization by user ID
		User user = urp.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Organization organization = orp.findByUser(user)
				.orElseThrow(() -> new RuntimeException("Organization not found"));

		// Create new request
		Requesting request = new Requesting();
		request.setFoodOffer(foodOffer);
		request.setStatus(Requesting.RequestStatus.PENDING);
		request.setRequestDate(LocalDateTime.now());
		request.setOrg(organization);

		return requestRepo.save(request);
	}

	public Requesting updateRequestStatus(long requestId, Requesting.RequestStatus status) {
		Requesting request = requestRepo.findById(requestId)
				.orElseThrow(() -> new EntityNotFoundException("Request not found"));
		request.setStatus(status);
		return requestRepo.save(request);
	}

	public String updateOrganizationDetails(long orgId, String name, String description, String contactEmail,
			String contactPhone, String address) {

// Fetch the organization by ID
		Organization organization = orp.findById(orgId)
				.orElseThrow(() -> new IllegalArgumentException("Organization not found"));

// Update only the relevant fields
		organization.setName(name);
		organization.setDescription(description);
		organization.setContactEmail(contactEmail);
		organization.setContactPhone(contactPhone);
		organization.setAddress(address);

// Save changes
		orp.save(organization);

		return "Organization details updated successfully";
	}
}
