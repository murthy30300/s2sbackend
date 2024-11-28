package com.klu.ss.repository;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.FoodOffer;
import com.klu.ss.model.enums.DonationStatus;
import com.klu.ss.model.enums.FoodType;

@Repository
public interface FoodOfferRepository extends JpaRepository<FoodOffer, Long> {
//    List<FoodOffer> findByUser_Uid(int uid);
//    Optional<FoodOffer> findByFoidAndUser_Uid(Long foid, Long uid);
	List<FoodOffer> findByUser_Uid(long uid);

	Optional<FoodOffer> findByFoidAndUser_Uid(Long foid, Long uid);

	List<FoodOffer> findAllByStatus(DonationStatus status);

	@Query("SELECT fo FROM FoodOffer fo " + "WHERE (:location IS NULL OR fo.location = :location) "
			+ "AND (:foodType IS NULL OR fo.foodType = :foodType) "
			+ "AND (:expiryDate IS NULL OR fo.expiryDate >= :expiryDate)")
	List<FoodOffer> findByFilters(@Param("location") String location, @Param("foodType") FoodType foodType,
			@Param("expiryDate") LocalDateTime expiryDate);
}