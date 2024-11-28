package com.klu.ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.klu.ss.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	  Optional<User> findUserByUsername(String username);


}
