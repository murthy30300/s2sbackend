package com.klu.ss.service;
import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Post;
import com.klu.ss.model.Profile;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.User;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public interface AdminService {
	 public String insertFoodOffer(FoodOffer fo);
	 public List<FoodOffer> getAllFoodOffers();
	 public String deleteFoodOffer(long foid);
	 public String updateFoodOffer(long foid,FoodOffer fo);
	 public Optional<FoodOffer> getFoodOfferById(long foid);
	 
	 public String insertOrganization(Organization org);
	 public List<Organization> getAllOrganizations();
	 public String deleteOrganization(long oid);
	 public String updateOrganization(long oid,Organization fo);
	 public Optional<Organization> getOrganizationByIdA(long oid);
	 
	 public Post createPost(String caption, MultipartFile image, Long uid);
	 public List<Post> getAllPost();
	 public String deletePost(long pid);
	 public String updatePost(long pid,Post p);
	 public Optional<Post> getPostById(long pid);
	 	 
	 public String insertRequesting(Requesting rst);
	 public List<Requesting> getAllRequesting();
	 public String deleteRequesting(long rqid);
	 public String updateRequesting(long rqid,Requesting fo);
	 public List<Requesting> getRequestingById(long rqid);
	 
	 public String insertUrgentNeed(UrgentNeed un);
	 public List<UrgentNeed> getAllUrgentNeeds();
	 public String deleteUrgentNeed(long unid);
	 public String updateUrgentNeed(long unid,UrgentNeed un);
	 public Optional<UrgentNeed> getUrgentNeedById(long unid);
	 
	 public String insertLogisticsProvider(LogisticsProvider lp);
	 public List<LogisticsProvider> getAllULogisticsProviders();
	 public String deleteLogisticsProvider(long lpid);
	 public String updateLogisticsProvider(long lpid,LogisticsProvider lp);
	 public Optional<LogisticsProvider> getLogisticsProviderrById(long lpid);
	 
	 public String insertRecipientStatus(RecipientStatus rs);
	 public List<RecipientStatus> getAllRecipientStatus();
	 public String deleteRecipientStatus(long rsid);
	 public String updateRecipientStatus(long rsid,RecipientStatus rs);
	 public Optional<RecipientStatus> getRecipientStatusById(long rsid);
	 
	 public String insertUser(User u);
	 public List<User> getAllUsers();
	 public String deleteUser(long uid);
	 public String updateUser(long uid,User u);
	 public Optional<User> getUserById(long uid);
	 
	 public String insertProfile(Profile p);
	 public List<Profile> getAllProfiles();
	 public String deleteProfile(long prid);
	 public String updateProfile(long prid,Profile p);
	 public Optional<Profile> getProfileById(long prid);
	 	
}
