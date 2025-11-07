package com.klu.ss.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.LogisticsProvider;
import com.klu.ss.model.Organization;
import com.klu.ss.model.Post;
import com.klu.ss.model.Profile;
import com.klu.ss.model.RecipientStatus;
import com.klu.ss.model.Requesting;
import com.klu.ss.model.UrgentNeed;
import com.klu.ss.model.User;
import com.klu.ss.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // FoodOffer Endpoints
    @PostMapping("/foodOffer")
    public ResponseEntity<String> addFoodOffer(@RequestBody FoodOffer foodOffer) {
        return ResponseEntity.ok(adminService.insertFoodOffer(foodOffer));
    }

    @GetMapping("/foodOffers")
    public ResponseEntity<List<FoodOffer>> getAllFoodOffers() {
        return ResponseEntity.ok(adminService.getAllFoodOffers());
    }

    @GetMapping("/foodOffer/{id}")
    public ResponseEntity<Optional<FoodOffer>> getFoodOfferById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getFoodOfferById(id));
    }

    @PutMapping("/foodOffer/{id}")
    public ResponseEntity<String> updateFoodOffer(@PathVariable long id, @RequestBody FoodOffer foodOffer) {
        return ResponseEntity.ok(adminService.updateFoodOffer(id, foodOffer));
    }

    @DeleteMapping("/foodOffer/{id}")
    public ResponseEntity<String> deleteFoodOffer(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteFoodOffer(id));
    }

    //--------------------------------------------------------------------------------------------------//
    @PostMapping("/organization")
    public ResponseEntity<String> addOrganization(@RequestBody Organization organization) {
        return ResponseEntity.ok(adminService.insertOrganization(organization));
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<Map<String, Object>>> getAllOrganizations() {
        List<Organization> organizations = adminService.getAllOrganizations();
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (Organization org : organizations) {
            Map<String, Object> response = new HashMap<>();
            response.put("oid", org.getId());
            response.put("name", org.getName());
            response.put("type", org.getType());
            response.put("description", org.getDescription());
            response.put("registrationNumber", org.getRegistrationNumber());
            response.put("headName", org.getHeadName());
            response.put("contactEmail", org.getContactEmail());
            response.put("contactPhone", org.getContactPhone());
            response.put("address", org.getAddress());
            response.put("score", org.getScore());
            response.put("totalDonations", org.getTotalDonations());
            response.put("totalReceived", org.getTotalReceived());
            response.put("badge", org.getBadge());
            responseList.add(response);
        }

        return ResponseEntity.ok(responseList);
    }


    @GetMapping("/organization/{id}")
    public ResponseEntity<Optional<Organization>> getOrganizationByIdw(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getOrganizationByIdA(id));
    }

    @PutMapping("/organization/{id}")
    public ResponseEntity<String> updateOrganization(@PathVariable long id, @RequestBody Organization organization) {
        return ResponseEntity.ok(adminService.updateOrganization(id, organization));
    }

    @DeleteMapping("/organization/{id}")
    public ResponseEntity<String> deleteOrganization(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteOrganization(id));
    }

    //--------------------------------------------------------------------------------------------------//
    @PostMapping("/post")
    public ResponseEntity<String> addPost(
            @RequestParam("caption") String caption,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam("uid") Long uid) {
        Post post = adminService.createPost(caption, image, uid);
        return ResponseEntity.ok("Post created successfully!");
    }
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(adminService.getAllPost());
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getPostById(id));
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable long id, @RequestBody Post post) {
        return ResponseEntity.ok(adminService.updatePost(id, post));
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deletePost(id));
    }

    // ----------------------------------------------------------------------------------------//
    @PostMapping("/requesting")
    public ResponseEntity<String> addRequesting(@RequestBody Requesting requesting) {
        return ResponseEntity.ok(adminService.insertRequesting(requesting));
    }

    @GetMapping("/requestings")
    public ResponseEntity<List<Requesting>> getAllRequesting() {
        return ResponseEntity.ok(adminService.getAllRequesting());
    }

    @GetMapping("/requesting/{id}")
    public ResponseEntity<List<Requesting>> getRequestingByRequesterId(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getRequestingById(id));
    }

    @PutMapping("/requesting/{id}")
    public ResponseEntity<String> updateRequesting(@PathVariable long id, @RequestBody Requesting requesting) {
        return ResponseEntity.ok(adminService.updateRequesting(id, requesting));
    }

    @DeleteMapping("/requesting/{id}")
    public ResponseEntity<String> deleteRequesting(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteRequesting(id));
    }

   //-------------------------------------------------------------------------------------------//
    @PostMapping("/urgentNeed")
    public ResponseEntity<String> addUrgentNeed(@RequestBody UrgentNeed urgentNeed) {
        return ResponseEntity.ok(adminService.insertUrgentNeed(urgentNeed));
    }

    @GetMapping("/urgentNeeds")
    public ResponseEntity<List<UrgentNeed>> getAllUrgentNeeds() {
    	List<UrgentNeed> un = adminService.getAllUrgentNeeds();
    	for(UrgentNeed x:un) {
    		System.out.println("************");
    		System.out.println(x.getId());
    	}
        return ResponseEntity.ok(adminService.getAllUrgentNeeds());
    }

    @GetMapping("/urgentNeed/{id}")
    public ResponseEntity<Optional<UrgentNeed>> getUrgentNeedById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getUrgentNeedById(id));
    }

    @PutMapping("/urgentNeed/{id}")
    public ResponseEntity<String> updateUrgentNeed(@PathVariable long id, @RequestBody UrgentNeed urgentNeed) {
        return ResponseEntity.ok(adminService.updateUrgentNeed(id, urgentNeed));
    }

    @DeleteMapping("/urgentNeed/{id}")
    public ResponseEntity<String> deleteUrgentNeed(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteUrgentNeed(id));
    }
//-------------------------------------------------
    @PostMapping("/profile")
    public ResponseEntity<String> addProfile(@RequestBody Profile prp) {
        return ResponseEntity.ok(adminService.insertProfile(prp));
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<Profile>> getAllProfile() {
        return ResponseEntity.ok(adminService.getAllProfiles());
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Optional<Profile>> getProfileById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getProfileById(id));
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<String> updateProfile(@PathVariable long prid, @RequestBody Profile prp) {
        return ResponseEntity.ok(adminService.updateProfile(prid, prp));
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable long prid) {
        return ResponseEntity.ok(adminService.deleteProfile(prid));
    }

    // ----------------------------------------------------------------------
    @PostMapping("/logisticsProvider")
    public ResponseEntity<String> addLogisticsProvider(@RequestBody LogisticsProvider logisticsProvider) {
        return ResponseEntity.ok(adminService.insertLogisticsProvider(logisticsProvider));
    }

    @GetMapping("/logisticsProviders")
    public ResponseEntity<List<LogisticsProvider>> getAllLogisticsProviders() {
        return ResponseEntity.ok(adminService.getAllULogisticsProviders());
    }

    @GetMapping("/logisticsProvider/{id}")
    public ResponseEntity<Optional<LogisticsProvider>> getLogisticsProviderById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getLogisticsProviderrById(id));
    }

    @PutMapping("/logisticsProvider/{id}")
    public ResponseEntity<String> updateLogisticsProvider(@PathVariable long id, @RequestBody LogisticsProvider logisticsProvider) {
        return ResponseEntity.ok(adminService.updateLogisticsProvider(id, logisticsProvider));
    }

    @DeleteMapping("/logisticsProvider/{id}")
    public ResponseEntity<String> deleteLogisticsProvider(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteLogisticsProvider(id));
    }

    // RecipientStatus Endpoints
    @PostMapping("/recipientStatus")
    public ResponseEntity<String> addRecipientStatus(@RequestBody RecipientStatus recipientStatus) {
        return ResponseEntity.ok(adminService.insertRecipientStatus(recipientStatus));
    }

    @GetMapping("/recipientStatuses")
    public ResponseEntity<List<RecipientStatus>> getAllRecipientStatuses() {
        return ResponseEntity.ok(adminService.getAllRecipientStatus());
    }

    @GetMapping("/recipientStatus/{id}")
    public ResponseEntity<Optional<RecipientStatus>> getRecipientStatusById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getRecipientStatusById(id));
    }

    @PutMapping("/recipientStatus/{id}")
    public ResponseEntity<String> updateRecipientStatus(@PathVariable long id, @RequestBody RecipientStatus recipientStatus) {
        return ResponseEntity.ok(adminService.updateRecipientStatus(id, recipientStatus));
    }

    @DeleteMapping("/recipientStatus/{id}")
    public ResponseEntity<String> deleteRecipientStatus(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteRecipientStatus(id));
    }

    // User Endpoints
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        return ResponseEntity.ok(adminService.insertUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(adminService.getUserById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable long id, @RequestBody User user) {
        return ResponseEntity.ok(adminService.updateUser(id, user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(adminService.deleteUser(id));
    }
}
