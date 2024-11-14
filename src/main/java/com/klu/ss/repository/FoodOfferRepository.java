package com.klu.ss.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.FoodOffer;

@Repository
public interface FoodOfferRepository extends JpaRepository<FoodOffer, Integer> {
    List<FoodOffer> findByUser_Uid(int uid);
    Optional<FoodOffer> findByFoidAndUser_Uid(Long foid, Long uid);
}