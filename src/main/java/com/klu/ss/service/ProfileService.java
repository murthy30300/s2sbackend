package com.klu.ss.service;
import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;

import com.klu.ss.model.Profile;
public interface ProfileService {
	public String updateUserDetails(Long uid, MultipartFile profilePic, MultipartFile bannerPic, String name,
			LocalDate dateOfBirth, String phone, String address, String badge, String username);
	public Profile getProfileByUserId(long userId);
	public Profile getProfileDetailsByUsername(String username);
}
