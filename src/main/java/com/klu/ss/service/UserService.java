package com.klu.ss.service;

import com.klu.ss.model.User;

public interface UserService {
	public String signup(User u);
	public User login(String u,String p);
//	public String login(String u,String p);
	public Integer getUserIdByUsername(String username);
}
