package com.klu.ss.service.Implementations;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.klu.ss.model.*;
import com.klu.ss.repository.*;
import com.klu.ss.service.ProfileService;

import jakarta.transaction.Transactional;

@Service
public class ProfileServiceImp implements ProfileService {
	@Autowired
	UserRepo urp;
	@Autowired
	ProfileRepo prp;
	@Autowired
	private Cloudinary cloudinary;
	@Autowired
	private static final long MAX_SIZE = 5 * 1024 * 1024;
	@Transactional
	public String updateUserDetails(Integer uid, MultipartFile profilePic, MultipartFile bannerPic, String name,
			LocalDate dateOfBirth, String phone, String address, String badge) {

		// uid = 2;
		Profile profile = prp.getByUid(uid).orElse(new Profile());
		System.err.println(profile);

		if (profile.getUser() == null) { // If no associated User
			User user = urp.findById(uid).orElseThrow(() -> new IllegalArgumentException("User not found"));
			profile.setUser(user); // Associate profile with the user
			System.err.println(user);
		}
		System.out.println(profile);
		// profile.setUser(new User(uid));
		profile.setName(name);
		profile.setDateOfBirth(dateOfBirth);
		profile.setPhone(phone);
		profile.setAddress(address);
		profile.setBadge(badge);
		profile.setUpdatedAt(LocalDateTime.now());
		String profilePicUrl = null;
		if (profilePic != null && !profilePic.isEmpty()) {
			if (profilePic.getSize() > MAX_SIZE) {
				throw new IllegalArgumentException("Profile picture is too large");
			}
			try {
				Map<?, ?> uploadResult = cloudinary.uploader().upload(profilePic.getBytes(), ObjectUtils.emptyMap());
				profilePicUrl = (String) uploadResult.get("url");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Error uploading profile picture");
			}
		}
		profile.setProfilePicUrl(profilePicUrl);
		String bannerPicUrl = null;
		if (bannerPic != null && !bannerPic.isEmpty()) {
			if (bannerPic.getSize() > MAX_SIZE) {
				throw new IllegalArgumentException("Banner picture is too large");
			}
			try {
				Map<?, ?> uploadResult = cloudinary.uploader().upload(bannerPic.getBytes(), ObjectUtils.emptyMap());
				bannerPicUrl = (String) uploadResult.get("url");
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Error uploading banner picture");
			}
		}
		profile.setBannerPicUrl(bannerPicUrl);
		if (profile.getPrid() == null) {
			profile.setCreatedAt(LocalDateTime.now());
		}
		prp.save(profile);
		return "User details updated successfully";

	}
	public Profile getProfileByUserId(int userId) {
		return prp.findByUserId(userId);
	}
	  public Profile getProfileDetailsByUsername(String username) {
	        Profile profile = prp.findByUserUsername(username);
	        if (profile != null) {
	            return new Profile(profile.getUser().getUsername(), profile.getProfilePicUrl(), profile.getBannerPicUrl());
	        }
	        return null;
	    }

}
