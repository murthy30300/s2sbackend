package com.klu.ss.repository;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klu.ss.model.Requesting;
public interface RequestRepo extends JpaRepository<Requesting, Long>  {
	// List<Requesting> findByFoodOffer_Id(int foodOfferId); // Find requests for a specific offer
	    List<Requesting> findByRequester_Prid(Long requesterId);
	    List<Requesting> findByFoodOffer_Foid(int foid);
}
