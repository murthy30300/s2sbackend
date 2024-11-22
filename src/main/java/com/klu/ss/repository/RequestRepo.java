package com.klu.ss.repository;
import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.ss.model.Requesting;

@Repository
public interface RequestRepo extends JpaRepository<Requesting, Long> {
    List<Requesting> findByRequester_Prid(Long requesterId);
    List<Requesting> findByFoodOffer_Foid(int foid);
    List<Requesting> findByFoodOffer_FoidAndStatus(int foid, Requesting.RequestStatus status);

}