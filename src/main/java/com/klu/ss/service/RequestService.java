package com.klu.ss.service;
import com.klu.ss.model.Requesting;
import java.util.*;
public interface RequestService {
    public boolean markAsCompleted(Long requestId);
    public boolean confirmRequest(Long requestId);
    public Requesting createRequest(int foodOfferId, Long requesterId);
    public List<Requesting> getRequestsByUser(Long requesterId);
    public List<Requesting> getRequestsForOffer(int offerId);
}
