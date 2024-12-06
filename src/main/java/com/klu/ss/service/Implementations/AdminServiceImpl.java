package com.klu.ss.service.Implementations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Post;
import com.klu.ss.model.Profile;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.User;
import com.klu.ss.repository.FoodOfferRepository;
import com.klu.ss.repository.LogisticsProviderRepository;
import com.klu.ss.repository.OrganizationRepo;
import com.klu.ss.repository.PostRepo;
import com.klu.ss.repository.ProfileRepo;
import com.klu.ss.repository.RecipentStatusRepository;
import com.klu.ss.repository.RequestRepo;
import com.klu.ss.repository.UrgentNeedRepository;
import com.klu.ss.repository.UserRepo;
import com.klu.ss.service.AdminService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private FoodOfferRepository foodOfferRepository;

    @Autowired
    private OrganizationRepo organizationRepository;

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private RequestRepo requestingRepository;

    @Autowired
    private UrgentNeedRepository urgentNeedRepository;

    @Autowired
    private LogisticsProviderRepository logisticsProviderRepository;

    @Autowired
    private RecipentStatusRepository recipientStatusRepository;

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private ProfileRepo prp;

    @Override
    public String insertFoodOffer(FoodOffer fo) {
        foodOfferRepository.save(fo);
        return "Food Offer inserted successfully!";
    }

    @Override
    public List<FoodOffer> getAllFoodOffers() {
        return foodOfferRepository.findAll();
    }

    @Override
    public String deleteFoodOffer(long foid) {
        foodOfferRepository.deleteById(foid);
        return "Food Offer deleted successfully!";
    }

    @Override
    public String updateFoodOffer(long foid, FoodOffer fo) {
        Optional<FoodOffer> existing = foodOfferRepository.findById(foid);
        if (existing.isPresent()) {
            FoodOffer updated = existing.get();
            updated.setDescription(fo.getDescription());
            updated.setLocation(fo.getLocation());
            updated.setQuantity(fo.getQuantity());
            updated.setExpiryDate(fo.getExpiryDate());
            updated.setFoodType(fo.getFoodType());
            updated.setPackagingType(fo.getPackagingType());
            updated.setPickupInstructions(fo.getPickupInstructions());
            updated.setPerishable(fo.isPerishable());
            updated.setDietaryNotes(fo.getDietaryNotes());
            updated.setStatus(fo.getStatus());
            updated.setLatitude(fo.getLatitude());
            updated.setLongitude(fo.getLongitude());
            foodOfferRepository.save(updated);
            return "Food Offer updated successfully!";
        }
        return "Food Offer not found!";
    }

    @Override
    public Optional<FoodOffer> getFoodOfferById(long foid) {
        return foodOfferRepository.findById(foid);
    }
    //--------------------------------------------------------------------------------------------------//

    @Override
    public String insertOrganization(Organization org) {
        organizationRepository.save(org);
        return "Organization inserted successfully!";
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    @Override
    public String deleteOrganization(long oid) {
        organizationRepository.deleteById(oid);
        return "Organization deleted successfully!";
    }

    @Override
    public String updateOrganization(long oid, Organization org) {
        Optional<Organization> existing = organizationRepository.findById(oid);
        if (existing.isPresent()) {
            Organization updated = existing.get();
            updated.setName(org.getName());
            updated.setDescription(org.getDescription());
            updated.setRegistrationNumber(org.getRegistrationNumber());
            updated.setHeadName(org.getHeadName());
            updated.setContactEmail(org.getContactEmail());
            updated.setContactPhone(org.getContactPhone());
            updated.setAddress(org.getAddress());
            organizationRepository.save(updated);
            return "Organization updated successfully!";
        }
        return "Organization not found!";
    }

    @Override
    public Optional<Organization> getOrganizationByIdA(long oid) {
        return organizationRepository.findById(oid);
    }
    //--------------------------------------------------------------------------------------------------//
    
    @Autowired
    private Cloudinary cloudinary;

    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB

    public Post createPost(String caption, MultipartFile image, Long uid) {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String imageUrl = null;
        if (image != null && !image.isEmpty()) {
            if (image.getSize() > MAX_IMAGE_SIZE) {
                throw new IllegalArgumentException("Image size exceeds 5MB");
            }

            try {
                Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
                imageUrl = (String) uploadResult.get("url");
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image to Cloudinary", e);
            }
        }

        Post post = new Post();
        post.setUser(user);
        post.setCaption(caption);
        post.setImageUrl(imageUrl);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setActive(true);

        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public String deletePost(long pid) {
        postRepository.deleteById(pid);
        return "Post deleted successfully!";
    }

    @Override
    public String updatePost(long pid, Post p) {
        Optional<Post> existing = postRepository.findById(pid);
        if (existing.isPresent()) {
            Post updated = existing.get();
            updated.setCaption(p.getCaption());
            updated.setImageUrl(p.getImageUrl());
            updated.setActive(p.isActive());
            postRepository.save(updated);
            return "Post updated successfully!";
        }
        return "Post not found!";
    }

    @Override
    public Optional<Post> getPostById(long pid) {
        return postRepository.findById(pid);
    }
    @Override
    public String insertRequesting(Requesting rst) {
        requestingRepository.save(rst);
        return "Requesting entry inserted successfully!";
    }

    @Override
    public List<Requesting> getAllRequesting() {
        return requestingRepository.findAll();
    }

    @Override
    public String deleteRequesting(long rqid) {
        requestingRepository.deleteById(rqid);
        return "Requesting entry deleted successfully!";
    }

    @Override
    public String updateRequesting(long rqid, Requesting rst) {
        Optional<Requesting> existing = requestingRepository.findById(rqid);
        if (existing.isPresent()) {
            Requesting updated = existing.get();
            updated.setRequestDate(rst.getRequestDate());
            updated.setStatus(rst.getStatus());
            updated.setOrg(rst.getOrg());
            updated.setFoodOffer(rst.getFoodOffer());
            updated.setRequester(rst.getRequester());
            requestingRepository.save(updated);
            return "Requesting entry updated successfully!";
        }
        return "Requesting entry not found!";
    }

    @Override
    public List<Requesting> getRequestingById(long rqid) {
        return requestingRepository.findByRequesterId(rqid);
    }
    @Override
    public String insertUrgentNeed(UrgentNeed un) {
        urgentNeedRepository.save(un);
        return "Urgent Need inserted successfully!";
    }

    @Override
    public List<UrgentNeed> getAllUrgentNeeds() {
        return urgentNeedRepository.findAll();
    }

    @Override
    public String deleteUrgentNeed(long unid) {
        urgentNeedRepository.deleteById(unid);
        return "Urgent Need deleted successfully!";
    }

    @Override
    public String updateUrgentNeed(long unid, UrgentNeed un) {
        Optional<UrgentNeed> existing = urgentNeedRepository.findById(unid);
        if (existing.isPresent()) {
            UrgentNeed updated = existing.get();
            updated.setTitle(un.getTitle());
            updated.setDescription(un.getDescription());
            updated.setQuantityNeeded(un.getQuantityNeeded());
            updated.setNeededBy(un.getNeededBy());
            updated.setEventType(un.getEventType());
            updated.setIsActive(un.getIsActive());
            urgentNeedRepository.save(updated);
            return "Urgent Need updated successfully!";
        }
        return "Urgent Need not found!";
    }

    @Override
    public Optional<UrgentNeed> getUrgentNeedById(long unid) {
        return urgentNeedRepository.findById(unid);
    }
    @Override
    public String insertLogisticsProvider(LogisticsProvider lp) {
        logisticsProviderRepository.save(lp);
        return "Logistics Provider inserted successfully!";
    }

    @Override
    public List<LogisticsProvider> getAllULogisticsProviders() {
        return logisticsProviderRepository.findAll();
    }

    @Override
    public String deleteLogisticsProvider(long lpid) {
        logisticsProviderRepository.deleteById((int) lpid);
        return "Logistics Provider deleted successfully!";
    }

    @Override
    public String updateLogisticsProvider(long lpid, LogisticsProvider lp) {
        Optional<LogisticsProvider> existing = logisticsProviderRepository.findById((int) lpid);
        if (existing.isPresent()) {
            LogisticsProvider updated = existing.get();
            updated.setName(lp.getName());
            updated.setLocation(lp.getLocation());
            updated.setLatitude(lp.getLatitude());
            updated.setLongitude(lp.getLongitude());
            updated.setContactNumber(lp.getContactNumber());
            updated.setIsAvailable(lp.getIsAvailable());
            logisticsProviderRepository.save(updated);
            return "Logistics Provider updated successfully!";
        }
        return "Logistics Provider not found!";
    }

    @Override
    public Optional<LogisticsProvider> getLogisticsProviderrById(long lpid) {
        return logisticsProviderRepository.findById((int) lpid);
    }
    @Override
    public String insertRecipientStatus(RecipientStatus rs) {
        recipientStatusRepository.save(rs);
        return "Recipient Status inserted successfully!";
    }

    @Override
    public List<RecipientStatus> getAllRecipientStatus() {
        return recipientStatusRepository.findAll();
    }

    @Override
    public String deleteRecipientStatus(long rsid) {
        recipientStatusRepository.deleteById(rsid);
        return "Recipient Status deleted successfully!";
    }

    @Override
    public String updateRecipientStatus(long rsid, RecipientStatus rs) {
        Optional<RecipientStatus> existing = recipientStatusRepository.findById(rsid);
        if (existing.isPresent()) {
            RecipientStatus updated = existing.get();
            updated.setTotalFoodReceived(rs.getTotalFoodReceived());
            updated.setPeopleFed(rs.getPeopleFed());
            updated.setSuccessfulRequests(rs.getSuccessfulRequests());
            updated.setLastUpdated(rs.getLastUpdated());
            recipientStatusRepository.save(updated);
            return "Recipient Status updated successfully!";
        }
        return "Recipient Status not found!";
    }

    @Override
    public Optional<RecipientStatus> getRecipientStatusById(long rsid) {
        return recipientStatusRepository.findById(rsid);
    }
    @Override
    public String insertUser(User u) {
        userRepository.save(u);
        return "User inserted successfully!";
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(long uid) {
        userRepository.deleteById(uid);
        return "User deleted successfully!";
    }

    @Override
    public String updateUser(long uid, User u) {
        Optional<User> existing = userRepository.findById(uid);
        if (existing.isPresent()) {
            User updated = existing.get();
            updated.setEmail(u.getEmail());
            updated.setUsername(u.getUsername());
            updated.setPassword(u.getPassword());
            updated.setRole(u.getRole());
            userRepository.save(updated);
            return "User updated successfully!";
        }
        return "User not found!";
    }

    @Override
    public Optional<User> getUserById(long uid) {
        return userRepository.findById(uid);
    }

	@Override
	public String insertProfile(Profile p) {
		  prp.save(p);
	        return "Profile inserted successfully!";
	}

	@Override
	public List<Profile> getAllProfiles() {
		return prp.findAll();
	}

	@Override
	public String deleteProfile(long prid) {
	 prp.deleteById(prid);
	 return "delete successfully";
	}

	@Override
	public String updateProfile(long prid, Profile p) {
		Optional<Profile> existing = prp.findById(prid);
        if (existing.isPresent()) {
            Profile updated = existing.get();
            p.setAddress(updated.getAddress());
            p.setBadge(updated.getBadge());
            p.setBannerPicUrl(updated.getBannerPicUrl());
            p.setCreatedAt(updated.getCreatedAt());
            p.setDateOfBirth(updated.getDateOfBirth());
            p.setName(updated.getName());
            p.setPhone(updated.getPhone());
            p.setPrid(updated.getPrid());
            p.setProfilePicUrl(updated.getProfilePicUrl());
            p.setScore(updated.getScore());
            p.setTotalDonations(updated.getTotalDonations());
            p.setTotalReceived(updated.getTotalReceived());
            p.setUpdatedAt(updated.getUpdatedAt());
            p.setUid(updated.getUid());
            p.setUsername(updated.getUsername());         
            prp.save(updated);
            return "User updated successfully!";
        }
        return "User not found!";
	}

	@Override
	public Optional<Profile> getProfileById(long prid) {
		return prp.findById(prid);
	}


}
