package com.klu.ss.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.Requesting;

@Repository
public interface RequestRepo extends JpaRepository<Requesting, Long> {
	@Query("SELECT r FROM Requesting r WHERE r.foodOffer.foid = :offerId")
	List<Requesting> findByFoodOffer_Foid(@Param("offerId") Long offerId);
	@Query("SELECT r FROM Requesting r WHERE r.foodOffer.foid = :offerId AND r.status = 'PENDING'")
	List<Requesting> findPendingRequestsByFoodOffer_Foid(@Param("offerId") Long offerId);


	

	@Query("SELECT r FROM Requesting r WHERE r.requester.prid = :requesterId")
	List<Requesting> findByRequester_Prid(@Param("requesterId") Long requesterId);


	List<Requesting> findByFoodOffer_FoidAndStatus(long foid, Requesting.RequestStatus status);

	@Query("SELECT r FROM Requesting r WHERE r.org.id = :organizationId AND r.status = :status")
	List<Requesting> findByRequesterIdAndStatus(@Param("organizationId") long organizationId,
			@Param("status") Requesting.RequestStatus status);

	@Query("SELECT r FROM Requesting r WHERE r.org.id = :organizationId")
	List<Requesting> findByRequesterId(@Param("organizationId") long organizationId);

}