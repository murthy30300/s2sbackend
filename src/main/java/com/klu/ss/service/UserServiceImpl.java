package com.klu.ss.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.ss.model.*;
import com.klu.ss.repository.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo urp;
	private ProfileRepo prp;
	@Override
	@Transactional
	public String signup(User u) {
		urp.save(u);
		return "Signed up successfully";
	}

	public User login(String username, String password) {
		User user = urp.findByUsernameAndPassword(username, password);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public Integer getUserIdByUsername(String username) {
		User user = urp.findByUsername(username);
		if (user != null) {
			return user.getUid(); 
		}
		return null; 
	}
	public Optional<Profile> getUserProfile(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return null; 
        }

        User user = urp.findByUsername(username);
        if (user == null) {
            return null;
        }

        return prp.getByUid(user.getUid());
    }

}
