package com.klu.ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klu.ss.model.Organization;
import com.klu.ss.model.User;

public interface OrganizationRepo extends JpaRepository<Organization, Long>{
	 Organization findByUserUid(Long user_id);
	 Optional<Organization> findByUser(User user);
	// Organization findByUser(User user);
	 @Query("SELECT o FROM Organization o WHERE o.user = :user")
	 Organization findByUserp(@Param("user") User user);
	 @Query("SELECT o FROM Organization o JOIN FETCH o.user WHERE o.oid = :oid")
	 Optional<Organization> findByOid(@Param("oid") Long oid);
	 

}
