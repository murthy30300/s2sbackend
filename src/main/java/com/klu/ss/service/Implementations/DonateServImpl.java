package com.klu.ss.service.Implementations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.ss.model.FoodDonation;
import com.klu.ss.repository.*;
import com.klu.ss.service.*;
import jakarta.transaction.Transactional;
@Service
public class DonateServImpl implements FoodDonationService{
	@Autowired
	private FoodDonationRepository fdr;
    @Transactional
    public FoodDonation saveFoodDonation(FoodDonation fd) {
        return fdr.save(fd);
    }
}
