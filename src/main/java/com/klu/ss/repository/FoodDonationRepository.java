package com.klu.ss.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.FoodDonation;

@Repository
public interface FoodDonationRepository extends JpaRepository<FoodDonation, Long> {
}