package com.klu.ss.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Post;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.enums.FoodType;

public interface RecipientOrgService {
    public List<FoodOffer> getFilteredDonations(String location, FoodType foodType, LocalDateTime expiryDate);
    public List<LogisticsProvider> findNearbyLogistics(long donationId, long recipientId);
    public RecipientStatus getStats(long organizationId);
//    public List<Requesting> getRequestHistory(long organizationId, Requesting.RequestStatus status);
    public UrgentNeed createUrgentNeed(UrgentNeed urgentNeed);
    public Map<String, Object> calculateDistributionPlan(long requestId);
    public Post addSuccessStory(long organizationId, String story, MultipartFile image);
    public Requesting createFoodRequest(long foodOfferId, long userId);
    public Requesting updateRequestStatus(long requestId, Requesting.RequestStatus status);
	public List<Requesting> getRequestHistory(long organizationId, Requesting.RequestStatus status);
	public String updateOrganizationDetails(long uid, String name, String description, String contactEmail,
			String contactPhone, String address);
}
