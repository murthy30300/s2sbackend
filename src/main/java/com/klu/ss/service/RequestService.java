package com.klu.ss.service;
import com.klu.ss.model.Requesting;
import java.util.*;

public interface RequestService {
    boolean markAsCompleted(Long requestId);
    boolean confirmRequest(Long requestId);
    boolean cancelRequest(Long requestId);
    Requesting createRequest(long foodOfferId, long requesterId);
    List<Requesting> getRequestsByUser(Long requesterId);
    List<Requesting> getRequestsForOffer(long offerId);
    public boolean updateRequestStatus(Long requestId, Requesting.RequestStatus status);
}