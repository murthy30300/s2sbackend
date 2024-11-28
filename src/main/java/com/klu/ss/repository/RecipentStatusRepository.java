package com.klu.ss.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klu.ss.model.RecipientStatus;

public interface RecipentStatusRepository extends JpaRepository<RecipientStatus, Long> {
	@Query("SELECT rs FROM RecipientStatus rs WHERE rs.organization.uid = :organizationId")
	Optional<RecipientStatus> findByOrganizationId(@Param("organizationId") long organizationId);

}
