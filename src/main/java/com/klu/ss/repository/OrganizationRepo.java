package com.klu.ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.ss.model.Organization;
import com.klu.ss.model.User;

public interface OrganizationRepo extends JpaRepository<Organization, Long>{
	 Organization findByUserUid(Long user_id);
	 Optional<Organization> findByUser(User user);

}
