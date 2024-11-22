package com.klu.ss.service;
import com.klu.ss.model.Requesting;
import java.util.*;

public interface RequestService {
    boolean markAsCompleted(Long requestId);
    boolean confirmRequest(Long requestId);
    boolean cancelRequest(Long requestId);
    Requesting createRequest(int foodOfferId, int requesterId);
    List<Requesting> getRequestsByUser(Long requesterId);
    List<Requesting> getRequestsForOffer(int offerId);
    public boolean updateRequestStatus(Long requestId, Requesting.RequestStatus status);
}
